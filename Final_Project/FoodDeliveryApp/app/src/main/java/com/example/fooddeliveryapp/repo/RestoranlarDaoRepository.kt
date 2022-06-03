package com.example.fooddeliveryapp.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.fooddeliveryapp.entity.Restoranlar
import com.google.firebase.database.*

class RestoranlarDaoRepository{
    var restoranlarListesi:MutableLiveData<List<Restoranlar>>
    var refRestoranlar:DatabaseReference

    init{
        val db = FirebaseDatabase.getInstance()
        refRestoranlar = db.getReference("restoranlar")
        restoranlarListesi = MutableLiveData()
    }

    fun restoranlariGetir():MutableLiveData<List<Restoranlar>>{
        return restoranlarListesi
    }

    fun tumRestoranlariGetir(){
        refRestoranlar.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = ArrayList<Restoranlar>()
                for(rest in snapshot.children){
                    val restoran = rest.getValue(Restoranlar::class.java)
                    if (restoran != null){
                        restoran.restoran_id = rest.key
                        list.add(restoran)
                    }
                }
                restoranlarListesi.value = list
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }

    fun restoranAra(aramaKelimesi:String){
        refRestoranlar.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = ArrayList<Restoranlar>()
                for(rest in snapshot.children){
                    val restoran = rest.getValue(Restoranlar::class.java)
                    if (restoran != null){
                        if(restoran.restoran_mutfak_turu!!.lowercase().contains(aramaKelimesi.lowercase())){
                            restoran.restoran_id = rest.key
                            Log.e("BULUNAN RESTORAN AD: ", restoran.restoran_adi!!)
                            list.add(restoran)
                        }
                    }
                }
                restoranlarListesi.value = list
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }



    fun restoranEkle(restoran_adi:String,
                     restoran_resim_adi:String,
                     restoran_mutfak_turu:String,
                     restoran_puan:String){
        val newRestoran = Restoranlar("",restoran_adi,restoran_resim_adi, restoran_mutfak_turu, restoran_puan)
        refRestoranlar.push().setValue(newRestoran)
    }
}