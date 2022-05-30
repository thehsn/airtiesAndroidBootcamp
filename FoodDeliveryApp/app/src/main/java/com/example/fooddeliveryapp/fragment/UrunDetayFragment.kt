package com.example.fooddeliveryapp.fragment

import android.opengl.Visibility
import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.adapter.YemeklerAdapter
import com.example.fooddeliveryapp.databinding.CardTasarimBinding
import com.example.fooddeliveryapp.databinding.FragmentUrunDetayBinding
import com.example.fooddeliveryapp.entity.SepetYemekler
import com.example.fooddeliveryapp.viewmodel.UrunDetayFragmentViewModel
import com.squareup.picasso.Picasso


class UrunDetayFragment : Fragment() {

    private lateinit var tasarim:FragmentUrunDetayBinding
    private lateinit var viewModel:UrunDetayFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_urun_detay, container, false)
        tasarim.toolbarBaslik = "Ürün Detay"

        val bundle:UrunDetayFragmentArgs by navArgs()
        val gelenUrun = bundle.yemek

        tasarim.yemekNesnesi = gelenUrun
        tasarim.textViewFiyat.text = gelenUrun.yemek_fiyat.toString()
        resimGoster(gelenUrun.yemek_resim_adi, tasarim)


        viewModel.yemeklerListesi.observe(viewLifecycleOwner){
            //aynı restorana ait diğer ürünleri listele
        }

        lateinit var sepetYemek : SepetYemekler
        var isContains = false

        tasarim.buttonPlus.setOnClickListener {
            viewModel.sepeteEkle(gelenUrun.yemek_adi, gelenUrun.yemek_resim_adi, gelenUrun.yemek_fiyat, viewModel.getUser())
        }


        return tasarim.root
    }


    fun resimGoster(resimAdi:String, tasarim: FragmentUrunDetayBinding){
        val url = "http://kasimadalan.pe.hu/yemekler/resimler/$resimAdi"
        Picasso.get()
            .load(url)
            .resize(512,512)
            .rotate(0f)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.hata)
            .into(tasarim.imageViewUrun)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:UrunDetayFragmentViewModel by viewModels()
        viewModel = tempViewModel
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.e("bitti", "destroy")
    }

    override fun onStop() {
        super.onStop()
        Log.e("bitti", "stop")
        onDestroy()
        onDetach()
    }
}