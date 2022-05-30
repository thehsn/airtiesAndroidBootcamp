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
        t.textViewAdet.text = sepetYemek.yemek_siparis_adet.toString()

        resimGoster(sepetYemek.yemek_resim_adi,t)


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