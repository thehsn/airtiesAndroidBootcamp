package com.example.fooddeliveryapp.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.fooddeliveryapp.entity.Yemekler
import com.example.fooddeliveryapp.entity.YemeklerCevap
import com.example.fooddeliveryapp.retrofit.ApiUtils
import com.example.fooddeliveryapp.retrofit.YemeklerDaoInterface
import com.example.kisileruygulamasi.entity.CRUDCevap
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class YemeklerDaoRepository {
    var yemeklerListesi: MutableLiveData<List<Yemekler>>
    var ydao:YemeklerDaoInterface

    init {
        ydao = ApiUtils.getYemeklerDaoInterface()
        yemeklerListesi = MutableLiveData()
    }

    fun yemekleriGetir() : MutableLiveData<List<Yemekler>> {
        return yemeklerListesi
    }

    fun tumYemekleriAl(){
        ydao.tumYemekleriGetir().enqueue(object : Callback<YemeklerCevap>{
            override fun onResponse(
                call: Call<YemeklerCevap>,
                response: Response<YemeklerCevap>
            ) {
                val liste = response.body().yemekler
                yemeklerListesi.value = liste
            }

            override fun onFailure(call: Call<YemeklerCevap>?, t: Throwable?) {

            }
        })
    }
}