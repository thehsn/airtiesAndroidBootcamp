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
import com.example.fooddeliveryapp.databinding.CardTasarimKampanyaBinding
import com.example.fooddeliveryapp.entity.Restoranlar
import com.example.fooddeliveryapp.fragment.AnasayfaFragment
import com.example.fooddeliveryapp.fragment.AnasayfaFragmentDirections
import com.example.fooddeliveryapp.setSafeOnClickListener
import com.example.fooddeliveryapp.viewmodel.RestoranFragmentViewModel
import com.example.kisileruygulamasi.viewmodel.AnasayfaFragmentViewModel
import com.squareup.picasso.Picasso

class RestoranlarAdapter(var mContext: Context,
                         var restoranlarListesi:List<Restoranlar>,
                         var viewModel:AnasayfaFragmentViewModel,
                         var activity:AnasayfaFragment) : RecyclerView.Adapter<RestoranlarAdapter.CardRestoranTutucu>(){
    inner class CardRestoranTutucu(tasarim:CardRestoranBinding):RecyclerView.ViewHolder(tasarim.root){
        var tasarim:CardRestoranBinding
        init {
            this.tasarim = tasarim
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardRestoranTutucu {
        val layoutInflater = LayoutInflater.from(mContext)
        val tasarim:CardRestoranBinding = DataBindingUtil.inflate(layoutInflater, R.layout.card_restoran, parent, false)
        return CardRestoranTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: CardRestoranTutucu, position: Int) {
        val restoran = restoranlarListesi.get(position)
        val t = holder.tasarim
        t.restoranNesnesi = restoran
        resimGoster(restoran.restoran_resim_adi!!, t)

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


        t.restoranCard.setSafeOnClickListener {
            if(restoran.restoran_adi.equals("Bootcamp Restaurant")){
                val gecis = AnasayfaFragmentDirections.actionAnasayfaFragmentToRestoranFragment(viewModel.getUser(),"retrofit",restoran.restoran_adi!!)
                Navigation.findNavController(it).navigate(gecis)
            } else{
                val gecis = AnasayfaFragmentDirections.actionAnasayfaFragmentToRestoranFragment(viewModel.getUser(),"firebase",restoran.restoran_adi!!)
                Navigation.findNavController(it).navigate(gecis)
            }
        }

        t.floatingActionButtonFavori.setSafeOnClickListener {
            Log.e("buton içerisi","------------------")
            viewModel.favoriEkle(database_type!!,
                restoran.restoran_adi!!,
                "restoran",
                restoran.restoran_adi!!, viewModel.getUser())
            t.floatingActionButtonFavori.visibility = View.GONE
            t.floatingActionButtonDelete.visibility = View.VISIBLE
        }

        t.floatingActionButtonDelete.setSafeOnClickListener {
            viewModel.restoranaAitFavorileriSil(restoran.restoran_adi!!, viewModel.getUser())
            t.floatingActionButtonDelete.visibility = View.GONE
            t.floatingActionButtonFavori.visibility = View.VISIBLE
        }
    }

    fun resimGoster(resimAdi:String, tasarim: CardRestoranBinding){
        if (resimAdi != null){
            Picasso.get()
                .load(resimAdi)
                //.resize(100,100)
                .rotate(0f)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.hata)
                .into(tasarim.imageViewRestoranResim)
        }
    }

    override fun getItemCount(): Int {
        return restoranlarListesi.size
    }


}