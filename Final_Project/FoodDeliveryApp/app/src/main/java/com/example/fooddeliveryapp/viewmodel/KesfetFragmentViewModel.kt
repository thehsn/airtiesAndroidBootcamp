package com.example.fooddeliveryapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fooddeliveryapp.entity.Mutfaklar
import com.example.fooddeliveryapp.repo.MutfaklarDaoRepository
import com.google.firebase.database.FirebaseDatabase

class KesfetFragmentViewModel:ViewModel() {

    val mrepo = MutfaklarDaoRepository()
    var mutfaklarListesi = MutableLiveData<List<Mutfaklar>>()

    init {
        mutfaklariYukle()
        mutfaklarListesi = mrepo.mutfaklariGetir()
    }

    fun mutfaklariYukle(){
        mrepo.tumMutfaklariAl()
    }
}