package com.example.fooddeliveryapp.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.databinding.CardRestoranBinding
import com.example.fooddeliveryapp.databinding.CardTasarimBinding
import com.example.fooddeliveryapp.databinding.CardTasarimKampanyaBinding
import com.example.fooddeliveryapp.entity.FirebaseYemekler
import com.example.fooddeliveryapp.entity.Kampanyalar
import com.example.fooddeliveryapp.fragment.RestoranFragment
import com.example.fooddeliveryapp.viewmodel.RestoranFragmentViewModel
import com.example.kisileruygulamasi.viewmodel.AnasayfaFragmentViewModel
import com.squareup.picasso.Picasso

class FirebaseYemeklerAdapter(var mContext: Context, // we need for inflating our layout
                              var firebaseYemeklerListesi:List<FirebaseYemekler>, // livedata
                              var viewModel: RestoranFragmentViewModel,
                              val activity: RestoranFragment,
                              val restoran:String
)
    : RecyclerView.Adapter<FirebaseYemeklerAdapter.CardTasarimTutucu>() {

    inner class CardTasarimTutucu(tasarim: CardTasarimBinding) : RecyclerView.ViewHolder(tasarim.root) {

        var tasarim: CardTasarimBinding

        init {
            this.tasarim = tasarim
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val layoutInflater = LayoutInflater.from(mContext)

        val tasarim: CardTasarimBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.card_tasarim, parent, false
        )
        return CardTasarimTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        val t = holder.tasarim
        //val firebaseYemek = firebaseYemeklerListesi.get(position)
        val yemekList = ArrayList<FirebaseYemekler>()
        for(f in firebaseYemeklerListesi){
            if(f.restoran_ad.equals(restoran)){
                yemekList.add(f)
            }
        }

        val yemek = yemekList.get(position)
        Log.e("restoran: ", "${yemek.resim_adi.toString()} - ${yemek.restoran_ad}")
        t.textUrunAd.text = yemek.yemek_ad.toString()
        t.textUrunFiyat.text = yemek.yemek_fiyat.toString()
        resimGoster(yemek.resim_adi,t)





    }

    fun resimGoster(resimAdi:String?, tasarim: CardTasarimBinding){
        if(resimAdi != null){
            Picasso.get()
                .load(resimAdi)
                .resize(512,512)
                .rotate(0f)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.hata)
                .into(tasarim.imageView)
        }

    }

    override fun getItemCount(): Int {
        var counter = 0
        for(f in firebaseYemeklerListesi){
            if(f.restoran_ad.equals(restoran)){
                counter++
            }
        }
        return counter
    }


}