package com.example.fooddeliveryapp.entity

import com.google.firebase.database.IgnoreExtraProperties
import java.io.Serializable

@IgnoreExtraProperties
data class Favoriler(var favori_id:String? = "",
                     var favori_database_type:String? = "",
                     var favori_yemek_ad:String? = "",
                     var type:String? = "",
                     var restoran_ad:String? = "",
                     var favori_user:String? = ""):Serializable{

}
