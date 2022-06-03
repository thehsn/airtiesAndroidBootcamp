package com.example.fooddeliveryapp.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.activity.SigninActivity
import com.example.fooddeliveryapp.databinding.FragmentProfilBinding
import com.google.firebase.auth.FirebaseAuth

class ProfilFragment : Fragment() {
    private lateinit var tasarim:FragmentProfilBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_profil, container, false)

        val user_id = arguments?.getString("user_id")
        val email_id = arguments?.getString("email_id")

        tasarim.userId = user_id
        tasarim.emailId = email_id

        tasarim.buttonLogout.setOnClickListener {
            //Logout from app
            FirebaseAuth.getInstance().signOut()

            activity?.let {
                val intent = Intent(it, SigninActivity::class.java)
                it.startActivity(intent)
                it.finish()
            }

        }

        return tasarim.root
    }


}