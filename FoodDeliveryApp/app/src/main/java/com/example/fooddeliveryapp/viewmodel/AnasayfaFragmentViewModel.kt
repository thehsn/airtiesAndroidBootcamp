package com.example.kisileruygulamasi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fooddeliveryapp.entity.Kampanyalar
import com.example.fooddeliveryapp.entity.Yemekler
import com.example.fooddeliveryapp.repo.KampanyalarDaoRepository
import com.example.fooddeliveryapp.repo.YemeklerDaoRepository

class AnasayfaFragmentViewModel : ViewModel() {


    val krepo = KampanyalarDaoRepository()
    var kampanyalarListesi = MutableLiveData<List<Kampanyalar>>()

    init {
        kampanyalariYukle()
        kampanyalarListesi = krepo.kampanyalariGetir()

    }
/*
    fun ara(aramaKelimesi:String){
        yrepo.kisiAra(aramaKelimesi)
    }

    fun sil(kisi_id:Int){
        yrepo.kisiSil(kisi_id)
    }*/

    fun kampanyalariYukle(){
        krepo.tumKampanyalariAl()
    }


}