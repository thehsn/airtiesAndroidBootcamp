package com.example.fooddeliveryapp.retrofit

import com.example.fooddeliveryapp.entity.SepetYemeklerCevap
import com.example.kisileruygulamasi.entity.CRUDCevap
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface SepetlerDaoInterface {

    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    fun sepeteEkle(@Field("yemek_adi") yemek_adi:String,
                   @Field("yemek_resim_adi") yemek_resim_adi:String,
                   @Field("yemek_fiyat") yemek_fiyat:Int,
                   @Field("yemek_siparis_adet") yemek_siparis_adet:Int,
                   @Field("kullanici_adi") kullanici_adi:String) : Call<CRUDCevap>

    @POST("yemekler/sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    fun sepettekileriGetir(@Field("kullanici_adi") kullanici_adi: String) : Call<SepetYemeklerCevap>

    @POST("yemekler/sepettenYemekSil.php")
    @FormUrlEncoded
    fun sepettenYemekSil(@Field("sepet_yemek_id") sepet_yemek_id:Int,
                         @Field("kullanici_adi") kullanici_adi: String) : Call<CRUDCevap>

}