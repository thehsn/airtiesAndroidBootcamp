package com.example.fooddeliveryapp.repo

import androidx.lifecycle.MutableLiveData
import com.example.fooddeliveryapp.entity.FirebaseYemekler
import com.google.firebase.database.*

class FirebaseYemeklerDaoRepository {
    var firebaseYemeklerListesi: MutableLiveData<List<FirebaseYemekler>>
    var refYemekler:DatabaseReference

    init {
        val db = FirebaseDatabase.getInstance()
        refYemekler = db.getReference("yemekler")
        firebaseYemeklerListesi = MutableLiveData()
    }

    fun firebaseYemeklerListesiniGetir():MutableLiveData<List<FirebaseYemekler>>{
        return firebaseYemeklerListesi
    }

    fun tumYemekleriAl(){
        refYemekler.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val yemekList = ArrayList<FirebaseYemekler>()

                for (yemek in snapshot.children){
                    val firebaseYemek = yemek.getValue(FirebaseYemekler::class.java)
                    if (firebaseYemek != null){
                        firebaseYemek.yemek_id = yemek.key
                        yemekList.add(firebaseYemek)
                    }
                }

                firebaseYemeklerListesi.value = yemekList
            }
        })
    }
}