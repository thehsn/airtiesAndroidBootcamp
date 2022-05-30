package com.example.fooddeliveryapp.retrofit

import com.example.fooddeliveryapp.entity.YemeklerCevap
import retrofit2.Call
import retrofit2.http.GET

interface YemeklerDaoInterface {

    //http://kasimadalan.pe.hu/

    @GET("yemekler/tumYemekleriGetir.php")
    fun tumYemekleriGetir() : Call<YemeklerCevap>


}