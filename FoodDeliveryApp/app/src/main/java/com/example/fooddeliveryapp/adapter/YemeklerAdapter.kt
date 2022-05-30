package com.example.fooddeliveryapp.adapter

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddeliveryapp.databinding.CardTasarimBinding
import com.example.fooddeliveryapp.entity.Yemekler
import com.example.kisileruygulamasi.viewmodel.AnasayfaFragmentViewModel
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.navArgument
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.entity.SepetYemekler
import com.example.fooddeliveryapp.fragment.RestoranFragment
import com.example.fooddeliveryapp.fragment.RestoranFragmentDirections
import com.example.fooddeliveryapp.setSafeOnClickListener
import com.example.fooddeliveryapp.viewmodel.RestoranFragmentViewModel
import com.example.fooddeliveryapp.viewmodel.SepetFragmentViewModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.*
import kotlin.concurrent.thread

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
        val yemek = yemeklerListesi.get(position)
        val t = holder.tasarim
        var adet = 0
        var flag = false
        var sepet_id:Int? = null

        if (yemek == null)
            Log.e("yemek", "yemek.yemek_adi")

        viewModel.sepettekilerListesi.observe(activity, Observer {
           if(it != null){
                for (sepet in it) {
                    if (sepet.yemek_adi.equals(yemek.yemek_adi)) {
                        sepet_id = sepet.sepet_yemek_id
                        adet = sepet.yemek_siparis_adet
                        t.textViewNumber.text = adet.toString()
                    }
                }
            }else{ Log.e("sıfırelemam", "sıfır")}
        } )


        t.yemekNesnesi = yemek
        t.textUrunFiyat.text = "${yemek.yemek_fiyat} ₺"
        resimGoster(yemek.yemek_resim_adi, holder.tasarim)

        t.satirCard.setOnClickListener {
            val gecis = RestoranFragmentDirections.actionRestoranFragmentToUrunDetayFragment(yemek = yemek, viewModel.getUser())
            Navigation.findNavController(it).navigate(gecis)
        }

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
