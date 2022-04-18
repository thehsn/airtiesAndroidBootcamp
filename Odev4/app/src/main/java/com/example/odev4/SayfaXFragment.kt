package com.example.odev4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.odev4.databinding.FragmentSayfaXBinding

class SayfaXFragment : Fragment() {
    private lateinit var tasarim:FragmentSayfaXBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        tasarim = FragmentSayfaXBinding.inflate(inflater,container,false)

        tasarim.buttonGoY.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_sayfaXFragment_to_sayfaYFragment)
        }

        return tasarim.root
    }

}