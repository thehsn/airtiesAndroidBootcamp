package com.example.fooddeliveryapp.entity

import com.google.firebase.database.IgnoreExtraProperties
import java.io.Serializable

@IgnoreExtraProperties
data class Mutfaklar(var mutfak_id:String? = "",
                       var mutfak_adi:String? = "",
                       var mutfak_resim_adi:String? = "") : Serializable {

}