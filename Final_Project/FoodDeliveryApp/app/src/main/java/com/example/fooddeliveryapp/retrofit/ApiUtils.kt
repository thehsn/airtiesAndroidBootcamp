package com.example.fooddeliveryapp.retrofit

class ApiUtils {
    companion object{
        val BASE_URL = "http://kasimadalan.pe.hu/"


        fun getYemeklerDaoInterface() : YemeklerDaoInterface{
            return RetrofitClient.getClient(BASE_URL).create(YemeklerDaoInterface::class.java)
        }

        fun getSepetlerDaoInterface() : SepetlerDaoInterface{
            return RetrofitClient.getClient(BASE_URL).create(SepetlerDaoInterface::class.java)
        }
    }
}