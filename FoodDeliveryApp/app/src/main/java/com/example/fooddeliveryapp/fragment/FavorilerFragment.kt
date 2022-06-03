package com.example.fooddeliveryapp.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.adapter.RestoranlarAdapter
import com.example.fooddeliveryapp.adapter.UrunlerAdapter
import com.example.fooddeliveryapp.databinding.FragmentFavorilerBinding
import com.example.fooddeliveryapp.entity.Restoranlar
import com.example.fooddeliveryapp.viewmodel.FavorilerFragmentViewModel


class FavorilerFragment : Fragment() {
    private lateinit var tasarim:FragmentFavorilerBinding
    private lateinit var viewModel:FavorilerFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_favoriler, container, false)

        val restoranlar = ArrayList<Restoranlar>()
        viewModel.restoranlarListesi.observe(viewLifecycleOwner){
            if(it != null){
                for(fav in viewModel.favorilerListesi.value!!){
                    for (rest in it){
                        if (fav.favori_yemek_ad.equals(rest.restoran_adi)&& fav.favori_user.equals(viewModel.getUser())){
                            restoranlar.add(rest)
                        }
                    }
                }
                tasarim.adapter = UrunlerAdapter(requireContext(), restoranlar.toList(),viewModel,this)
            }
        }


        return tasarim.root
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:FavorilerFragmentViewModel by viewModels()
        viewModel = tempViewModel
    }
}