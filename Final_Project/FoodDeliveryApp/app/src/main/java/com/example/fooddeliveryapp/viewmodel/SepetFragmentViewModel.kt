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

    private lateinit var user:String

    init {
        user = state.get<String>("user_id").toString()
        sepettekileriYukle()
        sepettekilerListesi = srepo.sepettekilerListesiGetir()
    }

    fun getUser():String{
        return user
    }

    fun sepeteEkle(yemek_ad:String,
                   yemek_resim_adi:String,
                   yemek_fiyat:Int,
                   adet:Int,
                   kullanici_adi:String){


        //if it's already exists then delete from cart
        if(sepettekilerListesi.value != null){
            for (yemek in sepettekilerListesi.value!!) {
                if (yemek.yemek_adi.equals(yemek_ad)) {
                    sepettenSil(yemek.sepet_yemek_id,yemek.kullanici_adi)
                }
            }
        }

        //add to cart with new number
        srepo.sepeteEkle(yemek_ad,
            yemek_resim_adi,
            yemek_fiyat,
            adet,
            kullanici_adi)
    }

    fun sepettekileriYukle(){
        srepo.sepettekileriGetir(user)
    }

    fun sepettenSil(sepet_yemek_id:Int, kullanici_adi: String){
        srepo.sepettenYemekSil(sepet_yemek_id,kullanici_adi)
    }


}