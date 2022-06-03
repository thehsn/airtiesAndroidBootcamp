package com.example.fooddeliveryapp.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.adapter.RestoranlarAdapter
import com.example.fooddeliveryapp.adapter.YemeklerAdapter
import com.example.fooddeliveryapp.databinding.FragmentUrunDetayBinding
import com.example.fooddeliveryapp.entity.SepetYemekler
import com.example.fooddeliveryapp.viewmodel.UrunDetayFragmentViewModel
import com.google.android.material.snackbar.Snackbar
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

        var adet = 0
        viewModel.sepettekilerListesi.observe(viewLifecycleOwner){
            if(it != null) {
                for(sepet in it){
                    if(sepet.yemek_adi.equals(gelenUrun.yemek_adi)){
                        Log.e("sepet-yemek:" ," ${sepet.yemek_adi} - ${gelenUrun.yemek_adi}")
                        adet = sepet.yemek_siparis_adet
                        tasarim.editTextNumber.hint = adet.toString()
                        Log.e("sepet-yemek-adet:" ," ${adet}")
                    }
                }
            }
        }

        tasarim.floatingActionButtonCart.setOnClickListener {
            if(tasarim.editTextNumber.text.isEmpty()){
                Snackbar.make(it,"Please enter a number", Snackbar.LENGTH_SHORT).show()
            }else{
                adet = tasarim.editTextNumber.text.toString().toInt() + 1
                tasarim.editTextNumber.setText(adet.toString())
            }
        }

        tasarim.floatingActionButtonCart2.setOnClickListener {
            if(tasarim.editTextNumber.text.isEmpty()){
                Snackbar.make(it,"Please enter a number", Snackbar.LENGTH_SHORT).show()
            }else {
                adet = tasarim.editTextNumber.text.toString().toInt() - 1
                tasarim.editTextNumber.setText(adet.toString())
            }
        }

        tasarim.button.setOnClickListener {
            if (tasarim.editTextNumber.text.isEmpty()) {
                Snackbar.make(it, "Please enter a number", Snackbar.LENGTH_SHORT).show()
            }else if(tasarim.editTextNumber.text.equals("0")){
                Snackbar.make(it, "Please enter a number", Snackbar.LENGTH_SHORT).show()
            }

            else {
                viewModel.sepeteEkle(
                    gelenUrun.yemek_adi,
                    gelenUrun.yemek_resim_adi,
                    gelenUrun.yemek_fiyat,
                    tasarim.editTextNumber.text.toString().toInt(),
                    viewModel.getUser()
                )

            }
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