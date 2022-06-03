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
import com.example.fooddeliveryapp.adapter.FirebaseYemeklerAdapter
import com.example.fooddeliveryapp.adapter.YemeklerAdapter
import com.example.fooddeliveryapp.databinding.FragmentRestoranBinding
import com.example.fooddeliveryapp.viewmodel.RestoranFragmentViewModel


class RestoranFragment : Fragment() {
    private lateinit var tasarim: FragmentRestoranBinding
    private lateinit var viewModel:RestoranFragmentViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_restoran, container, false)

        tasarim.rv.layoutManager = StaggeredGridLayoutManager(2 , StaggeredGridLayoutManager.VERTICAL)

        val bundle:RestoranFragmentArgs by navArgs()
        val user_id = bundle.userId
        val restoran = bundle.restoran
        val database_type = bundle.databaseType

        if(database_type.equals("retrofit")){
            viewModel.yemeklerListesi.observe(viewLifecycleOwner){
                val adapter = YemeklerAdapter(requireContext(),it,viewModel,this)
                tasarim.rv.adapter = adapter
            }
        } else{
            //firebase için yaz
            viewModel.firebaseYemeklerListesi.observe(viewLifecycleOwner){
                Log.e("size", it.size.toString())
                val adapter = FirebaseYemeklerAdapter(requireContext(),it,viewModel,this,restoran)
                tasarim.rv.adapter = adapter
            }
        }



        return tasarim.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:RestoranFragmentViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onResume() {
        super.onResume()
        viewModel.sepettekileriYukle()
    }

    override fun onStop() {
        super.onStop()
    }

}