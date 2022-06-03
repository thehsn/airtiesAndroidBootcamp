package com.example.fooddeliveryapp.entity

import com.google.firebase.database.IgnoreExtraProperties
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@IgnoreExtraProperties
data class Kampanyalar(var kampanya_id:String? = "",
                       var kampanya_adi:String? = "",
                       var kampanya_resim_adi:String? = "",
                       var kampanya_aciklamasi:String? = "") : Serializable {

}
