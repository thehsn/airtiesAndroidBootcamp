package com.example.sampleecommerceapp

import java.io.Serializable

data class Products(var productId:Int,
                    var procuctName:String,
                    var productCode:String,
                    var imageName:String,
                    var productPrice:Double ) : Serializable {
}