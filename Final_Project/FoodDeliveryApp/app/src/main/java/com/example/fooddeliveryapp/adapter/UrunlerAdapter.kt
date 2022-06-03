package com.example.fooddeliveryapp.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.databinding.CardRestoranBinding
import com.example.fooddeliveryapp.databinding.CardTasarimBinding
import com.example.fooddeliveryapp.entity.Favoriler
import com.example.fooddeliveryapp.entity.Restoranlar
import com.example.fooddeliveryapp.fragment.AnasayfaFragmentDirections
import com.example.fooddeliveryapp.fragment.FavorilerFragment
import com.example.fooddeliveryapp.fragment.FavorilerFragmentDirections
import com.example.fooddeliveryapp.fragment.RestoranFragment
import com.example.fooddeliveryapp.setSafeOnClickListener
import com.example.fooddeliveryapp.viewmodel.FavorilerFragmentViewModel
import com.squareup.picasso.Picasso

class UrunlerAdapter(var mContext: Context,
                     var restoranlarListesi:List<Restoranlar>,
                     var viewModel: FavorilerFragmentViewModel,
                     val activity:FavorilerFragment)

    : RecyclerView.Adapter<UrunlerAdapter.CardTasarimTutucu>() {

    inner class CardTasarimTutucu(tasarim:CardRestoranBinding) : RecyclerView.ViewHolder(tasarim.root){
        var tasarim:CardRestoranBinding
        init {
            this.tasarim = tasarim
        }
    }


    override fun getItemCount(): Int {
        return restoranlarListesi.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val layoutInflater = LayoutInflater.from(mContext)
        val tasarim: CardRestoranBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.card_restoran, parent, false
        )

        return CardTasarimTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        val restoran = restoranlarListesi.get(position)
        val t = holder.tasarim
        t.restoranNesnesi = restoran
        resimGoster(restoran.restoran_resim_adi!!, t)

        t.floatingActionButtonFavori.visibility = View.GONE
        t.floatingActionButtonDelete.visibility = View.VISIBLE

        var database_type:String? = null
        if(restoran.restoran_adi.equals("Bootcamp Restaurant")){
            database_type = "retrofit"
        }else{
            database_type = "firebase"
        }

        var favori_id:String? = null
        viewModel.favorilerListesi.observe(activity, Observer {
            if (it != null) {
                for (favori in it) {
                    if (favori.favori_yemek_ad.equals(restoran.restoran_adi) &&
                        favori.favori_user.equals(viewModel.getUser())) {
                        Log.e("found:", "${restoran.restoran_adi}")
                        favori_id = favori.favori_id
                        database_type = favori.favori_database_type

                        t.floatingActionButtonFavori.visibility = View.GONE
                        t.floatingActionButtonDelete.visibility = View.VISIBLE
                    }
                }
            }
        })

        t.floatingActionButtonDelete.setSafeOnClickListener {
            if (favori_id != null){
                viewModel.favoridenSil(favori_id!!)
            }else{
                Log.e("favori delete id null", "$favori_id")
            }

            t.floatingActionButtonDelete.visibility = View.GONE
            t.floatingActionButtonFavori.visibility = View.VISIBLE
        }

        t.floatingActionButtonFavori.setSafeOnClickListener {
            viewModel.favoriEkle("firebase", restoran.restoran_adi!!, "restoran",restoran.restoran_adi!!, viewModel.getUser())
            t.floatingActionButtonFavori.visibility = View.GONE
            t.floatingActionButtonDelete.visibility = View.VISIBLE
        }

        t.restoranCard.setSafeOnClickListener {
            if(restoran.restoran_adi.equals("Bootcamp Restaurant")){
                val gecis = FavorilerFragmentDirections.actionFavorilerFragmentToRestoranFragment(viewModel.getUser(),"retrofit",restoran.restoran_adi!!)
                Navigation.findNavController(it).navigate(gecis)
            } else{
                val gecis = FavorilerFragmentDirections.actionFavorilerFragmentToRestoranFragment(viewModel.getUser(),"firebase", restoran.restoran_adi!!)
                Navigation.findNavController(it).navigate(gecis)
            }
        }

    }

    fun resimGoster(resimAdi:String, tasarim: CardRestoranBinding){
        Picasso.get()
            .load(resimAdi)
            //.resize(512,512)
            .rotate(0f)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.hata)
            .into(tasarim.imageViewRestoranResim)
    }

}