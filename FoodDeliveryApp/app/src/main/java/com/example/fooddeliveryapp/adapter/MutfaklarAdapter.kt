package com.example.fooddeliveryapp.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.databinding.CardTasarimMutfaklarBinding
import com.example.fooddeliveryapp.entity.Kampanyalar
import com.example.fooddeliveryapp.entity.Mutfaklar
import com.example.fooddeliveryapp.viewmodel.KesfetFragmentViewModel
import com.example.kisileruygulamasi.viewmodel.AnasayfaFragmentViewModel
import com.squareup.picasso.Picasso

class MutfaklarAdapter (var mContext: Context, // we need for inflating our layout
                        var mutfaklarListesi:List<Mutfaklar>, // livedata
                        var viewModel:KesfetFragmentViewModel): RecyclerView.Adapter<MutfaklarAdapter.CardTasarimTutucu>() {

    inner class CardTasarimTutucu(tasarim: CardTasarimMutfaklarBinding) : RecyclerView.ViewHolder(tasarim.root) {
        var tasarim: CardTasarimMutfaklarBinding
        init {
            this.tasarim = tasarim
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        // this is where your inflate the layout and giving a look to our rows
        Log.e("adapter","adapter olustu")
        val layoutInflater = LayoutInflater.from(mContext)
        val tasarim: CardTasarimMutfaklarBinding = DataBindingUtil.inflate(layoutInflater,R.layout.card_tasarim_mutfaklar, parent, false)
        return CardTasarimTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        //assigning values to the views we created int the recycler_view_row layout file
        //based on the position of the recycler view

        val mutfak = mutfaklarListesi.get(position)

        val t = holder.tasarim
        t.mutfakNesnesi = mutfak
        resimGoster(mutfak.mutfak_resim_adi!!, holder.tasarim)

    }

    fun resimGoster(resimAdi:String, tasarim: CardTasarimMutfaklarBinding){
        //number of items
        if(resimAdi != null) {
            Picasso.get()
                .load(resimAdi)
                //.resize(100,100)
                .rotate(0f)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.hata)
                .into(tasarim.imageViewMutfak)
        }



    }

    override fun getItemCount(): Int {
        //number of items

        return mutfaklarListesi.size
    }
}