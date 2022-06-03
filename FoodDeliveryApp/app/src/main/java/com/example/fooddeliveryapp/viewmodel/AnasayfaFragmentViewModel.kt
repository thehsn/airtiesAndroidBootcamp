package com.example.kisileruygulamasi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.fooddeliveryapp.entity.*
import com.example.fooddeliveryapp.repo.*

class AnasayfaFragmentViewModel(private val state: SavedStateHandle) : ViewModel() {


    val krepo = KampanyalarDaoRepository()
    var kampanyalarListesi = MutableLiveData<List<Kampanyalar>>()

    val frepo = FavorilerDaoRepository()
    var favorilerListesi = MutableLiveData<List<Favoriler>>()

    val restoranRepo = RestoranlarDaoRepository()
    var restoranlarListesi = MutableLiveData<List<Restoranlar>>()

    val mutfaklarRepo = MutfaklarDaoRepository()
    var mutfaklarListesi = MutableLiveData<List<Mutfaklar>>()

    private lateinit var user:String

    init {
        user = state.get<String>("user_id").toString()

        kampanyalariYukle()
        kampanyalarListesi = krepo.kampanyalariGetir()

        favorileriYukle()
        favorilerListesi = frepo.favoriListesiniGetir()

        restoranlariYukle()
        restoranlarListesi = restoranRepo.restoranlariGetir()

        mutfaklariYukle()
        mutfaklarListesi = mutfaklarRepo.mutfaklariGetir()

    }

    fun getUser():String{
        return user
    }

    fun kampanyalariYukle(){
        krepo.tumKampanyalariAl()
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

    //restoranlar

    fun restoranlariYukle(){
        restoranRepo.tumRestoranlariGetir()
    }

    fun restoranAra(aramaKelimesi:String){
        restoranRepo.restoranAra(aramaKelimesi)
    }

    //mutfaklar

    fun mutfaklariYukle(){
        mutfaklarRepo.tumMutfaklariAl()
    }
}