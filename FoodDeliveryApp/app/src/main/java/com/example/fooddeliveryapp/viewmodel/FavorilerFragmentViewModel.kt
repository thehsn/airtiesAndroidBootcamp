package com.example.fooddeliveryapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.fooddeliveryapp.entity.Favoriler
import com.example.fooddeliveryapp.entity.Restoranlar
import com.example.fooddeliveryapp.repo.FavorilerDaoRepository
import com.example.fooddeliveryapp.repo.RestoranlarDaoRepository

class FavorilerFragmentViewModel(private val state: SavedStateHandle):ViewModel() {
    val frepo = FavorilerDaoRepository()
    var favorilerListesi = MutableLiveData<List<Favoriler>>()

    val restoranRepo = RestoranlarDaoRepository()
    var restoranlarListesi = MutableLiveData<List<Restoranlar>>()

    private lateinit var user:String

    init {
        user = state.get<String>("user_id").toString()

        favorileriYukle()
        favorilerListesi = frepo.favoriListesiniGetir()

        restoranlariYukle()
        restoranlarListesi = restoranRepo.restoranlariGetir()
    }

    fun getUser():String{
        return user
    }

    fun favorileriYukle(){
        frepo.tumFavorileriAl()
    }

    fun favoridenSil(favori_id:String){
        frepo.favoriSil(favori_id)
    }

    fun favoriEkle(database_type:String, yemek_ad: String, type:String, restoran:String,user:String){
        frepo.favoriEkle(database_type,yemek_ad, "restoran", restoran,user)
    }

    fun restoranaAitFavorileriSil(restoran:String,user:String){
        frepo.restoranaAitFavorileriSil(restoran,user)
    }

    //Restoranlar

    fun restoranlariYukle(){
        restoranRepo.tumRestoranlariGetir()
    }

    fun restoranAra(aramaKelimesi:String){
        restoranRepo.restoranAra(aramaKelimesi)
    }



    fun restoranEkle(restoran_adi:String,
                     restoran_resim_adi:String,
                     restoran_mutfak_turu:String,
                     restoran_puan:String){
        restoranRepo.restoranEkle(restoran_adi,restoran_resim_adi, restoran_mutfak_turu, restoran_puan)
    }

    fun restoranListesiAra(aramaKelimeleri:List<String>){
        var list = ArrayList<Restoranlar>()
        for(aramaKelimesi in aramaKelimeleri){
            restoranRepo.restoranAra(aramaKelimesi)
            Log.e("restoranListesiArama: ", "${restoranlarListesi.value?.size}")
            list.add(restoranlarListesi.value?.get(0)!!)
            Log.e("BENIM LISTEM: ", "${list.size}")
        }

        restoranlarListesi.value = list
    }


}