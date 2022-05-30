package com.example.fooddeliveryapp.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.fooddeliveryapp.entity.Kampanyalar
import com.example.fooddeliveryapp.entity.Yemekler
import com.example.fooddeliveryapp.entity.YemeklerCevap
import com.example.fooddeliveryapp.retrofit.ApiUtils
import com.example.fooddeliveryapp.retrofit.YemeklerDaoInterface
import com.google.firebase.database.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KampanyalarDaoRepository {
    var kampanyalarListesi:MutableLiveData<List<Kampanyalar>>
    var refKampanyalar: DatabaseReference

    init {
        val db = FirebaseDatabase.getInstance()
        refKampanyalar = db.getReference("kampanyalar")
        kampanyalarListesi = MutableLiveData()
    }

    fun kampanyalariGetir():MutableLiveData<List<Kampanyalar>>{
        return kampanyalarListesi
    }


    fun tumKampanyalariAl(){

        refKampanyalar.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) { // veri degisikliginde calisicak
                Log.e("hata", "hata yok")
                val liste = ArrayList<Kampanyalar>()

                for (d in snapshot.children){
                    val kampanya = d.getValue(Kampanyalar::class.java)
                    if (kampanya != null){
                        kampanya.kampanya_id = d.key // id ekleniyor
                        liste.add(kampanya)
                    }
                }

                kampanyalarListesi.value = liste
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("hata", "hata")
            } // hata oldugunda calisicak
        })
    }
}