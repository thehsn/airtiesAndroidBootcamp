package com.example.fooddeliveryapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.fooddeliveryapp.entity.SepetYemekler
import com.example.fooddeliveryapp.entity.Yemekler
import com.example.fooddeliveryapp.repo.SepetlerDaoRepository
import com.example.fooddeliveryapp.repo.YemeklerDaoRepository

class SepetFragmentViewModel (private val state: SavedStateHandle) :ViewModel() {

    var sepettekilerListesi = MutableLiveData<List<SepetYemekler>>()
    val srepo = SepetlerDaoRepository()
    //lateinit var user_id:String

    init {
        val user_id = state.get<String>("user_id")
        sepettekileriYukle(kullanici_adi = user_id!!)
        sepettekilerListesi = srepo.sepettekilerListesiGetir()
    }

    fun sepettekileriYukle(kullanici_adi: String){
        srepo.sepettekileriGetir(kullanici_adi)
    }

    fun sepeteEkle(yemek_ad:String,
                   yemek_resim_adi:String,
                   yemek_fiyat:Int,
                   kullanici_adi:String){

        var adet = 0

        if(sepettekilerListesi.value != null){
            for (yemek in sepettekilerListesi.value!!) {
                if (yemek.yemek_adi.equals(yemek_ad)) {
                    sepettenSil(yemek.sepet_yemek_id,yemek.kullanici_adi)
                    adet = yemek.yemek_siparis_adet + 1
                }
            }
        }

        srepo.sepeteEkle(yemek_ad,
            yemek_resim_adi,
            yemek_fiyat,
            adet,
            kullanici_adi)
    }

    fun sepettenSil(sepet_yemek_id:Int, kullanici_adi: String){
        srepo.sepettenYemekSil(sepet_yemek_id,kullanici_adi)
    }


}