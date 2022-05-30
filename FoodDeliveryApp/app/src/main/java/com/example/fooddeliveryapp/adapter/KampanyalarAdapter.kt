package com.example.fooddeliveryapp.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.databinding.CardTasarimKampanyaBinding

import com.example.fooddeliveryapp.entity.Kampanyalar
import com.example.kisileruygulamasi.viewmodel.AnasayfaFragmentViewModel
import com.squareup.picasso.Picasso

class KampanyalarAdapter (var mContext:Context, // we need for inflating our layout
                          var kampanyalarListesi:List<Kampanyalar>, // livedata
                          var viewModel:AnasayfaFragmentViewModel)
    : RecyclerView.Adapter<KampanyalarAdapter.CardTasarimTutucu>() {

    inner class CardTasarimTutucu(tasarim: CardTasarimKampanyaBinding) : RecyclerView.ViewHolder(tasarim.root) {

        //grabbing the views from our cardtasararim.xml layout file
        //kinda like oncreate method

        var tasarim: CardTasarimKampanyaBinding

        init {
            this.tasarim = tasarim
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        // this is where your inflate the layout and giving a look to our rows

        val layoutInflater = LayoutInflater.from(mContext)

        val tasarim: CardTasarimKampanyaBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.card_tasarim_kampanya, parent, false
        )
        return CardTasarimTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        //assigning values to the views we created int the recycler_view_row layout file
        //based on the position of the recycler view

        val kampanya = kampanyalarListesi.get(position)

        val t = holder.tasarim
        t.kampanyaNesnesi = kampanya

        resimGoster(kampanya.kampanya_resim_adi!!, holder.tasarim)


    }

    fun resimGoster(resimAdi:String, tasarim: CardTasarimKampanyaBinding){
        //number of items
            Picasso.get()
                .load(resimAdi)
                //.resize(100,100)
                .rotate(0f)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.hata)
                .into(tasarim.imageViewKampanya)


    }

    override fun getItemCount(): Int {
        //number of items
        return kampanyalarListesi.size
    }
}