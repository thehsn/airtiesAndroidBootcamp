package com.example.fooddeliveryapp.repo

import androidx.lifecycle.MutableLiveData
import com.example.fooddeliveryapp.entity.Favoriler
import com.google.firebase.database.*

class FavorilerDaoRepository {
    var favorilerListesi:MutableLiveData<List<Favoriler>>
    var refFavoriler:DatabaseReference

    init {
        val db = FirebaseDatabase.getInstance()
        refFavoriler = db.getReference("favoriler")
        favorilerListesi = MutableLiveData()
    }

    fun favoriListesiniGetir():MutableLiveData<List<Favoriler>>{return favorilerListesi}

    fun favoriEkle(database_type:String, yemek_ad:String, type:String, restoran:String, user:String){
        var flag = true // to check if fav already exist

        refFavoriler.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for(fav in snapshot.children){
                    if(fav != null){
                        if(fav.getValue(Favoriler::class.java)?.favori_yemek_ad.equals(yemek_ad) &&
                            fav.getValue(Favoriler::class.java)?.favori_user.equals(user)){
                            flag = false
                        }
                    }
                }

                if (flag){
                    var newFav = Favoriler("", database_type, yemek_ad, type, restoran,user)
                    refFavoriler.push().setValue(newFav)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    fun favoriSil(favori_id:String) {
        refFavoriler.child(favori_id).removeValue()
    }

    fun restoranaAitFavorileriSil(restoran:String, user:String){
        if(favorilerListesi.value != null){
            for(favs in favorilerListesi.value!!){
                if(favs.restoran_ad.equals(restoran) && favs.favori_user.equals(user)){
                    favoriSil(favs.favori_id!!)
                }
            }
        }

    }

    fun tumFavorileriAl(){
        refFavoriler.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val favsList = ArrayList<Favoriler>()

                for(f in snapshot.children){
                    val fav = f.getValue(Favoriler::class.java)
                    if (fav != null){
                        fav.favori_id = f.key
                        favsList.add(fav)
                    }
                }

                favorilerListesi.value = favsList
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}