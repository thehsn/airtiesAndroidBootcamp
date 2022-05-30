package com.example.fooddeliveryapp.entity

import com.google.firebase.database.IgnoreExtraProperties
import java.io.Serializable

@IgnoreExtraProperties
data class Restoranlar(var restoran_id:String? = "",
                       var restoran_adi:String? = "",
                       var restoran_resim_adi:String? = "",
                       var restoran_mutfak_turu_id:String? = "",
                       var restoran_puan:Int? = 0) : Serializable {

}