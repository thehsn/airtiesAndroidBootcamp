package com.example.fooddeliveryapp.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.fooddeliveryapp.entity.SepetYemekler
import com.example.fooddeliveryapp.entity.SepetYemeklerCevap
import com.example.fooddeliveryapp.entity.Yemekler
import com.example.fooddeliveryapp.retrofit.ApiUtils
import com.example.fooddeliveryapp.retrofit.SepetlerDaoInterface
import com.example.kisileruygulamasi.entity.CRUDCevap
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SepetlerDaoRepository {
    var sepettekilerListesi: MutableLiveData<List<SepetYemekler>>
    var sdao: SepetlerDaoInterface

    init {
        sdao = ApiUtils.getSepetlerDaoInterface()
        sepettekilerListesi = MutableLiveData()
    }

    fun sepettekilerListesiGetir(): MutableLiveData<List<SepetYemekler>> {
        return sepettekilerListesi
    }


    fun sepeteEkle(
        yemek_ad: String,
        yemek_resim_adi: String,
        yemek_fiyat: Int,
        adet:Int,
        kullanici_adi: String
    ) {


        sdao.sepeteEkle(
            yemek_ad,
            yemek_resim_adi,
            yemek_fiyat,
            adet,
            kullanici_adi
        ).enqueue(object : Callback<CRUDCevap> {

            override fun onResponse(call: Call<CRUDCevap>, response: Response<CRUDCevap>) {

                val basari = response.body().success
                val mesaj = response.body().message
                Log.e("Sepete eklendi", "$basari - $mesaj")
                sepettekileriGetir(kullanici_adi)

            }

            override fun onFailure(call: Call<CRUDCevap>?, t: Throwable?) {}
        })


    }

    fun sepettekileriGetir(kullanici_adi: String) {
        Log.e("repo", "calisti")
        sdao.sepettekileriGetir(kullanici_adi)
            .enqueue(object : Callback<SepetYemeklerCevap> {

                override fun onResponse(
                    call: Call<SepetYemeklerCevap>,
                    response: Response<SepetYemeklerCevap>
                ) {
                    val basari = response.body().success
                    val liste = response.body().sepet_yemekler
                    Log.e("Sepet çağrıldı boyut+:", "$basari - ${liste.size}")
                    sepettekilerListesi.value = liste
                }

                override fun onFailure(call: Call<SepetYemeklerCevap>?, t: Throwable?) {
                    Log.e("failure", "sepettekiler çağrılamadı (hepsi)")
                }
            })

    }

    fun sepettenYemekSil(sepet_yemek_id: Int, kullanici_adi: String) {
        sdao.sepettenYemekSil(sepet_yemek_id, kullanici_adi)
            .enqueue(object : Callback<CRUDCevap> {
                override fun onResponse(call: Call<CRUDCevap>, response: Response<CRUDCevap>) {
                    val basari = response.body().success
                    val mesaj = response.body().message
                    Log.e("Sepetten silindi", "$basari - $mesaj")
                    sepettekileriGetir(kullanici_adi)
                }

                override fun onFailure(call: Call<CRUDCevap>?, t: Throwable?) {
                    Log.e("Sepetten SİLİNMEDİ", "silinemedi")
                }
            })

    }

}