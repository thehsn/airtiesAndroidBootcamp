package com.example.fooddeliveryapp.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.adapter.SepetAdapter
import com.example.fooddeliveryapp.databinding.FragmentSepetBinding
import com.example.fooddeliveryapp.viewmodel.SepetFragmentViewModel

class SepetFragment : Fragment() {

    private lateinit var tasarim:FragmentSepetBinding
    private lateinit var viewModel:SepetFragmentViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_sepet, container, false)


        viewModel.sepettekilerListesi.observe(viewLifecycleOwner){
            Log.e("sepetfrag observeLive", it.size.toString())
            val adapter = SepetAdapter(requireContext(), it, viewModel)
            tasarim.sepetAdapter = adapter
        }

        return tasarim.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : SepetFragmentViewModel by viewModels()
        val userid = arguments?.getString("user_id")
        Log.e("USER", userid.toString())
        viewModel = tempViewModel
    }

    override fun onResume() {
        super.onResume()
        //Log.e("sepet fragment onresume", "calisti")
        //viewModel.sepettekileriYukle(arguments?.getString("user_id").toString())
    }

}