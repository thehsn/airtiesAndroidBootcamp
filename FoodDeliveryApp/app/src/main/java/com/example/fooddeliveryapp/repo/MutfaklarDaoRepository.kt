package com.example.fooddeliveryapp.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.fooddeliveryapp.entity.Mutfaklar
import com.google.firebase.database.*

class MutfaklarDaoRepository {
    var mutfaklarListesi:MutableLiveData<List<Mutfaklar>>
    var refMutfaklar:DatabaseReference

    init {
        val db = FirebaseDatabase.getInstance()
        refMutfaklar = db.getReference("mutfaklar")
        mutfaklarListesi = MutableLiveData()
    }

    fun mutfaklariGetir():MutableLiveData<List<Mutfaklar>>{
        return mutfaklarListesi
    }

    fun tumMutfaklariAl(){
        refMutfaklar.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val liste = ArrayList<Mutfaklar>()
                for(d in snapshot.children){
                    val mutfak = d.getValue(Mutfaklar::class.java)
                    //Log.e("mutfak", mutfak!!.mutfak_adi.toString())
                    if(mutfak != null){
                        mutfak.mutfak_id = d.key
                        liste.add(mutfak)
                    }
                }

                mutfaklarListesi.value = liste
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}