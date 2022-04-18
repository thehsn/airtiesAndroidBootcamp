package com.example.odev4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.odev4.databinding.FragmentSayfaBBinding

class SayfaBFragment : Fragment() {
    private lateinit var tasarim:FragmentSayfaBBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        tasarim = FragmentSayfaBBinding.inflate(inflater, container, false)

        tasarim.buttonBtoY.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_sayfaBFragment_to_sayfaYFragment)
        }

        return tasarim.root
    }

}