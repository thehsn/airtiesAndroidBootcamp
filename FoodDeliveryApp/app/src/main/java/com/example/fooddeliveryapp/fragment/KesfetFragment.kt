package com.example.fooddeliveryapp.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.adapter.KampanyalarAdapter
import com.example.fooddeliveryapp.adapter.MutfaklarAdapter
import com.example.fooddeliveryapp.databinding.FragmentKesfetBinding
import com.example.fooddeliveryapp.viewmodel.KesfetFragmentViewModel


class KesfetFragment : Fragment() {

    private lateinit var tasarim:FragmentKesfetBinding
    private lateinit var viewModel:KesfetFragmentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_kesfet, container, false)
        tasarim.kesfetToolbarBaslik = "Kampanyalar"
        (activity as AppCompatActivity).setSupportActionBar(tasarim.toolbar)
/*
        viewModel.mutfaklarListesi.observe(viewLifecycleOwner){
            Log.e("update", it.size.toString())
            val adapter = MutfaklarAdapter(requireContext(), it,viewModel)
            tasarim.mutfaklarAdapter = adapter
        }*/

        tasarim.rvmutfaklar.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)

        return tasarim.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        val tempViewModel:KesfetFragmentViewModel by viewModels()
        viewModel = tempViewModel
    }

}