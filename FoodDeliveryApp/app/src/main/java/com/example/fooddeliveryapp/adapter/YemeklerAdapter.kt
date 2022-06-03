package com.example.fooddeliveryapp.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddeliveryapp.databinding.CardTasarimBinding
import com.example.fooddeliveryapp.entity.Yemekler
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.fragment.RestoranFragment
import com.example.fooddeliveryapp.fragment.RestoranFragmentDirections
import com.example.fooddeliveryapp.setSafeOnClickListener
import com.example.fooddeliveryapp.viewmodel.RestoranFragmentViewModel
import com.squareup.picasso.Picasso

class YemeklerAdapter (var mContext:Context,
                       var yemeklerListesi:List<Yemekler>,
                       var viewModel:RestoranFragmentViewModel,
                       val activity:RestoranFragment)
    : RecyclerView.Adapter<YemeklerAdapter.CardTasarimTutucu>() {

    inner class CardTasarimTutucu(tasarim: CardTasarimBinding) :
        RecyclerView.ViewHolder(tasarim.root) {
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

        val yemek = yemeklerListesi.get(position)
        t.textUrunAd.text = yemek.yemek_adi
        t.textUrunFiyat.text = "${yemek.yemek_fiyat} ₺"
        resimGoster(yemek.yemek_resim_adi, holder.tasarim)


        var adet = 0 // number of profuct for later use
        var sepet_id:Int? = null    // cart_id for later use
        //observe the live data using RestoranFragment
        viewModel.sepettekilerListesi.observe(activity, Observer {
           if(it != null){
                for (sepet in it) {
                    if (sepet.yemek_adi.equals(yemek.yemek_adi) && sepet.kullanici_adi.equals(viewModel.getUser())) {
                        sepet_id = sepet.sepet_yemek_id
                        adet = sepet.yemek_siparis_adet
                    }
                }
               t.textViewNumber.text = adet.toString()
            }else{ Log.e("sıfırelemam", "sıfır")}
        } )

        var favori_id:String? = null
        viewModel.favorilerListesi.observe(activity, Observer {
            if (it != null){
                for (favori in it){
                    if(favori.favori_yemek_ad.equals((yemek.yemek_adi)) &&
                            favori.favori_user.equals(viewModel.getUser())){
                        favori_id = favori.favori_id
                    }
                }
            }
        })

        //if product is in fav list
        if(favori_id != null){
            t.floatingActionButtonFavDelete.visibility = View.VISIBLE
            t.floatingActionButtonFav.visibility = View.GONE
        }

        // when card view is clicked -> detail fragment
        t.satirCard.setOnClickListener {
            val gecis = RestoranFragmentDirections.actionRestoranFragmentToUrunDetayFragment(yemek = yemek, viewModel.getUser())
            Navigation.findNavController(it).navigate(gecis)
        }

        // when fav button is clicked -> add it to the firebase 'favoriler'
        t.floatingActionButtonFav.setSafeOnClickListener {
            viewModel.favoriEkle("retrofit", "Bootcamp Restaurant", "restoran","Bootcamp Restaurant", viewModel.getUser())
            viewModel.favoriEkle("retrofit",yemek.yemek_adi, "urun","Bootcamp Restaurant", viewModel.getUser())
            t.floatingActionButtonFav.visibility = View.GONE
            t.floatingActionButtonFavDelete.visibility = View.VISIBLE
        }

        // when other fav button clicked -> delete from firebase favoriler
        t.floatingActionButtonFavDelete.setSafeOnClickListener {
            if (favori_id != null){
                //
                viewModel.favoridenSil(favori_id!!)
            }else{
                Log.e("favori delete id null", "$favori_id")
            }

            t.floatingActionButtonFavDelete.visibility = View.GONE
            t.floatingActionButtonFav.visibility = View.VISIBLE
        }

        //plus button is clicked -> add it to retrofit cart
        t.floatingActionButtonCart.setSafeOnClickListener {
            if (adet > 0){ // urun varsa
                adet++
                viewModel.sepeteEkle(
                    yemek.yemek_adi,
                    yemek.yemek_resim_adi,
                    yemek.yemek_fiyat,
                    adet,
                    viewModel.getUser()
                )

                t.textViewNumber.text = adet.toString()

            }else{ // urun yoksa
                adet = 1
                t.textViewNumber.text = adet.toString()

                viewModel.sepeteEkle(
                    yemek.yemek_adi,
                    yemek.yemek_resim_adi,
                    yemek.yemek_fiyat,
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
                    yemek.yemek_adi,
                    yemek.yemek_resim_adi,
                    yemek.yemek_fiyat,
                    adet,
                    viewModel.getUser()
                )
            } else if (adet == 1) { // urunden son bir tane kalmissa
                adet--
                t.textViewNumber.text = adet.toString()
                Log.e("sepetid: ","$sepet_id")

                for (sepet in viewModel.sepettekilerListesi.value!!) {
                    if (sepet.yemek_adi.equals(yemek.yemek_adi)) { viewModel
                        .sepettenSil(sepet.sepet_yemek_id, viewModel.getUser()) }
                }
            }
            else{
                Log.e("sıfır", "sıfır")
            }
        }
    }


    fun resimGoster(resimAdi:String, tasarim: CardTasarimBinding){
        val url = "http://kasimadalan.pe.hu/yemekler/resimler/$resimAdi"
        Picasso.get()
            .load(url)
            .resize(512,512)
            .rotate(0f)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.hata)
            .into(tasarim.imageView)
    }

    override fun getItemCount(): Int {
        return yemeklerListesi.size
    }
}
