package com.example.fooddeliveryapp.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.adapter.YemeklerAdapter
import com.example.fooddeliveryapp.adapter.KampanyalarAdapter
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
        //tasarim.anasayfaToolbarBaslik = "Yemekler"
        //(activity as AppCompatActivity).setSupportActionBar(tasarim.topAppBar)


        viewModel.kampanyalarListesi.observe(viewLifecycleOwner){
            val adapterKampanya = KampanyalarAdapter(requireContext(),it,viewModel)
            tasarim.kampanyalarAdapter = adapterKampanya
        }


        tasarim.bootcampRestoranCard.setOnClickListener {
            Log.e("anasayfa","anasayfa to restoran user_id: ${user_id.toString()}")
            val gecis = AnasayfaFragmentDirections.actionAnasayfaFragmentToRestoranFragment(userId = user_id!!)
            Navigation.findNavController(it).navigate(gecis)
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