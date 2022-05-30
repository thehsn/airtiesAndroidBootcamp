package com.example.fooddeliveryapp.viewmodel

import android.util.Log
import androidx.core.os.bundleOf
import androidx.lifecycle.*
import com.example.fooddeliveryapp.entity.SepetYemekler
import com.example.fooddeliveryapp.entity.Yemekler
import com.example.fooddeliveryapp.repo.SepetlerDaoRepository
import com.example.fooddeliveryapp.repo.YemeklerDaoRepository
import kotlinx.coroutines.flow.combine

class RestoranFragmentViewModel(private val state: SavedStateHandle):ViewModel() {
    val yrepo = YemeklerDaoRepository()
    var yemeklerListesi = MutableLiveData<List<Yemekler>>()

    val srepo = SepetlerDaoRepository()
    var sepettekilerListesi = MutableLiveData<List<SepetYemekler>>()



    private lateinit var user:String

    init {
        user = state.get<String>("user_id").toString()
        Log.e("sepet restoran", user)
        yemekleriYukle()
        yemeklerListesi = yrepo.yemekleriGetir()

        sepettekileriYukle()
        Log.e("restoranview sepet","calisti")
        sepettekilerListesi= srepo.sepettekilerListesiGetir()
    }

    fun yemekleriYukle(){
        yrepo.tumYemekleriAl()
    }


    fun getUser():String{
        return user
    }

    fun sepeteEkle(yemek_ad:String,
                   yemek_resim_adi:String,
                   yemek_fiyat:Int,
                   adet:Int,
                   kullanici_adi:String){


        if(sepettekilerListesi.value != null){
            for (yemek in sepettekilerListesi.value!!) {
                if (yemek.yemek_adi.equals(yemek_ad)) {
                    sepettenSil(yemek.sepet_yemek_id,yemek.kullanici_adi)
                }
            }
        }

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