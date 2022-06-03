package com.example.fooddeliveryapp.viewmodel

import android.util.Log
import androidx.core.os.bundleOf
import androidx.lifecycle.*
import com.example.fooddeliveryapp.entity.Favoriler
import com.example.fooddeliveryapp.entity.FirebaseYemekler
import com.example.fooddeliveryapp.entity.SepetYemekler
import com.example.fooddeliveryapp.entity.Yemekler
import com.example.fooddeliveryapp.repo.FavorilerDaoRepository
import com.example.fooddeliveryapp.repo.FirebaseYemeklerDaoRepository
import com.example.fooddeliveryapp.repo.SepetlerDaoRepository
import com.example.fooddeliveryapp.repo.YemeklerDaoRepository
import kotlinx.coroutines.flow.combine

class RestoranFragmentViewModel(private val state: SavedStateHandle):ViewModel() {
    val yrepo = YemeklerDaoRepository()
    var yemeklerListesi = MutableLiveData<List<Yemekler>>()

    val srepo = SepetlerDaoRepository()
    var sepettekilerListesi = MutableLiveData<List<SepetYemekler>>()

    val frepo = FavorilerDaoRepository()
    var favorilerListesi = MutableLiveData<List<Favoriler>>()

    val firebaseYemeklerRepo = FirebaseYemeklerDaoRepository()
    var firebaseYemeklerListesi = MutableLiveData<List<FirebaseYemekler>>()

    private lateinit var user:String

    init {
        user = state.get<String>("user_id").toString()
        Log.e("sepet restoran", user)
        yemekleriYukle()
        yemeklerListesi = yrepo.yemekleriGetir()

        sepettekileriYukle()
        Log.e("restoranview sepet","calisti")
        sepettekilerListesi= srepo.sepettekilerListesiGetir()

        favorileriYukle()
        favorilerListesi = frepo.favoriListesiniGetir()

        firebaseYemeklerYukle()
        firebaseYemeklerListesi = firebaseYemeklerRepo.firebaseYemeklerListesiniGetir()
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


    //----------------------- firebase --------------------------------

    fun favorileriYukle(){
        frepo.tumFavorileriAl()
    }

    fun favoridenSil(favori_id:String){
        frepo.favoriSil(favori_id)
    }

    fun favoriEkle(database_type:String, yemek_ad: String, type:String, restoran:String, user:String){
        frepo.favoriEkle(database_type,yemek_ad, type, restoran, user)
    }

    fun restoranaAitFavorileriSil(restoran:String, user:String){
        frepo.restoranaAitFavorileriSil(restoran, user)
    }

    //yemekler
    fun firebaseYemeklerYukle(){
        firebaseYemeklerRepo.tumYemekleriAl()
    }

}