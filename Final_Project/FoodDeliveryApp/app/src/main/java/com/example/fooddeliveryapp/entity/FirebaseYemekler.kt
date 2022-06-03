package com.example.fooddeliveryapp.entity

import com.google.firebase.database.IgnoreExtraProperties
import java.io.Serializable

@IgnoreExtraProperties
data class FirebaseYemekler(var yemek_id:String? = "",
                            var yemek_ad:String? = "",
                            var yemek_fiyat:Int? = 0,
                            var restoran_ad:String? = "",
                            var resim_adi:String? = ""): Serializable {

}
