package com.example.fooddeliveryapp.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fooddeliveryapp.R


class KampanyalarFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kampanyalar, container, false)
    }

    override fun onStop() {
        super.onStop()
        Log.e("kapatildi" , "stop")

    }

    override fun onPause() {
        super.onPause()
        Log.e("kapatildi" , "pause")
    }
}