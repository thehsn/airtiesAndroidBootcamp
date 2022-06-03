package com.example.fooddeliveryapp.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.adapter.KampanyalarAdapter
import com.example.fooddeliveryapp.adapter.MutfaklarAdapter
import com.example.fooddeliveryapp.adapter.RestoranlarAdapter
import com.example.fooddeliveryapp.databinding.FragmentAnasayfaBinding
import com.example.kisileruygulamasi.viewmodel.AnasayfaFragmentViewModel



class AnasayfaFragment : Fragment(), SearchView.OnQueryTextListener {

    private lateinit var tasarim:FragmentAnasayfaBinding
    private lateinit var viewModel: AnasayfaFragmentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim = DataBindingUtil.inflate(inflater,R.layout.fragment_anasayfa, container, false)
        tasarim.anasayfaFragment = this

        val user_id = arguments?.getString("user_id")
        val email_id = arguments?.getString("email_id")

        Log.e("anasayfafragment", user_id.toString())

        tasarim.kampanyaRv.layoutManager = StaggeredGridLayoutManager(1 ,StaggeredGridLayoutManager.HORIZONTAL)

        viewModel.restoranlarListesi.observe(viewLifecycleOwner){
            tasarim.restoranlarAdapter = RestoranlarAdapter(requireContext(), it, viewModel, this)
        }

        viewModel.mutfaklarListesi.observe(viewLifecycleOwner){
            val adapter = MutfaklarAdapter(requireContext(),it,viewModel)
            tasarim.mutfaklarAdapter = adapter
        }

        return tasarim.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        val tempViewModel : AnasayfaFragmentViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        //viewModel.ara(query)
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        //viewModel.ara(newText)
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()


    }

    override fun onResume() {
        super.onResume()

    }
}