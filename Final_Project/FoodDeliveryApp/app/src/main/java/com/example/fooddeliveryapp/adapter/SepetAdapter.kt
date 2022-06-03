package com.example.fooddeliveryapp.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.databinding.CardTasarimBinding
import com.example.fooddeliveryapp.databinding.SepetCardTasarimBinding
import com.example.fooddeliveryapp.entity.SepetYemekler
import com.example.fooddeliveryapp.entity.Yemekler
import com.example.fooddeliveryapp.setSafeOnClickListener
import com.example.fooddeliveryapp.viewmodel.SepetFragmentViewModel
import com.squareup.picasso.Picasso

class SepetAdapter(var mContext:Context, var sepettekilerListesi:List<SepetYemekler>, var viewModel: SepetFragmentViewModel)
    :RecyclerView.Adapter<SepetAdapter.SepetCardTasarimTutucu>(){

        inner class SepetCardTasarimTutucu(tasarim:SepetCardTasarimBinding) : RecyclerView.ViewHolder(tasarim.root){
            var tasarim:SepetCardTasarimBinding
            init {
                this.tasarim = tasarim
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SepetCardTasarimTutucu {
        val layoutInflater = LayoutInflater.from(mContext)
        val tasarim:SepetCardTasarimBinding = DataBindingUtil.inflate(layoutInflater, R.layout.sepet_card_tasarim,parent,false)
        return SepetCardTasarimTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: SepetCardTasarimTutucu, position: Int) {
        val sepetYemek = sepettekilerListesi.get(position)
        val t = holder.tasarim
        t.textViewYemekAd.text = sepetYemek.yemek_adi
        t.textViewNumber.text = sepetYemek.yemek_siparis_adet.toString()
        t.textViewAdet.text = sepetYemek.yemek_fiyat.toString() + " ₺"
        resimGoster(sepetYemek.yemek_resim_adi,t)

        val sepetteki = sepettekilerListesi.get(position)
        var adet = sepetteki.yemek_siparis_adet

        //plus button is clicked -> add it to retrofit cart
        t.floatingActionButtonCart.setSafeOnClickListener {
            if (adet > 0){ // urun varsa
                adet++
                viewModel.sepeteEkle(
                    sepetteki.yemek_adi,
                    sepetteki.yemek_resim_adi,
                    sepetteki.yemek_fiyat,
                    adet,
                    viewModel.getUser()
                )

                t.textViewNumber.text = adet.toString()

            }else{ // urun yoksa
                adet = 1
                t.textViewNumber.text = adet.toString()

                viewModel.sepeteEkle(
                    sepetteki.yemek_adi,
                    sepetteki.yemek_resim_adi,
                    sepetteki.yemek_fiyat,
                    adet,
                    viewModel.getUser()
                )

            }

        }

        //minus button is clicked -> delete from retrofit cart
        t.floatingActionButtonCart2.setSafeOnClickListener {

            if (adet > 1) { // urun varsa
                adet--
                t.textViewNumber.text = adet.toString()

                viewModel.sepeteEkle(
                    sepetteki.yemek_adi,
                    sepetteki.yemek_resim_adi,
                    sepetteki.yemek_fiyat,
                    adet,
                    viewModel.getUser()
                )
            } else if (adet == 1) { // urunden son bir tane kalmissa
                adet--
                t.textViewNumber.text = adet.toString()
                Log.e("sepetid: ","${sepetteki.sepet_yemek_id}")

                for (sepet in viewModel.sepettekilerListesi.value!!) {
                    if (sepet.yemek_adi.equals(sepetteki.yemek_adi)) { viewModel
                        .sepettenSil(sepet.sepet_yemek_id, viewModel.getUser()) }
                }
            }
            else{
                Log.e("sıfır", "sıfır")
            }
        }


    }

    fun resimGoster(resimAdi:String, tasarim: SepetCardTasarimBinding){
        val url = "http://kasimadalan.pe.hu/yemekler/resimler/$resimAdi"
        Picasso.get()
            .load(url)
            .resize(512,512)
            .rotate(0f)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.hata)
            .into(tasarim.imageViewYemek)
    }

    override fun getItemCount(): Int {
        return sepettekilerListesi.size
    }


}