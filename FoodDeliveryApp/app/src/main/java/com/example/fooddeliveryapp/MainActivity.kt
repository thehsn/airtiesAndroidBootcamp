package com.example.fooddeliveryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.fooddeliveryapp.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var mAuth:FirebaseAuth
    private lateinit var tasarim:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mAuth = FirebaseAuth.getInstance()
        val user = mAuth.currentUser

        tasarim = ActivityMainBinding.inflate(layoutInflater)

        //splash screen
        //setContentView(R.layout.activity_splash_screen)
        setContentView(tasarim.root)

        val user_id = intent.getStringExtra("user_id")
        val email_id = intent.getStringExtra("email_id")

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        NavigationUI.setupWithNavController(tasarim.bottomNavigationView,navHostFragment.navController)

        val bundleStart = bundleOf("user_id" to user_id, "email_id" to email_id)
        navHostFragment.findNavController().navigate(R.id.anasayfaFragment,bundleStart)

        tasarim.bottomNavigationView.background = null // arkaplandaki golgeyi kaldırmak için
        tasarim.bottomNavigationView.menu.getItem(2).isEnabled = false // ortada yer alan placeholder'ın tıklanıllmasını engeller

        //on selected
        tasarim.bottomNavigationView.setOnItemSelectedListener { item->
            when(item.itemId) {
                R.id.anasayfaFragment -> {
                    // Respond to navigation item 1 click
                    val bundle = bundleOf("user_id" to user_id, "email_id" to email_id)
                    tasarim.navHostFragment.findNavController().navigate(R.id.anasayfaFragment,bundle)
                    true
                }
                R.id.kesfetFragment -> {
                    // Respond to navigation item 2 click
                    val bundle = bundleOf("user_id" to user_id, "email_id" to email_id)
                    tasarim.navHostFragment.findNavController().navigate(R.id.kesfetFragment,bundle)
                    true
                }
                R.id.kampanyalarFragment -> {
                    // Respond to navigation item 3 click
                    val bundle = bundleOf("user_id" to user_id, "email_id" to email_id)
                    tasarim.navHostFragment.findNavController().navigate(R.id.kampanyalarFragment,bundle)
                    true
                }
                R.id.profilFragment -> {
                    // Respond to navigation item 4 click
                    val bundle = bundleOf("user_id" to user_id, "email_id" to email_id)
                    tasarim.navHostFragment.findNavController().navigate(R.id.profilFragment, bundle)
                    true
                }
                else -> false
            }
        }


        //animation
        val animation = AnimationUtils.loadAnimation(this, R.anim.circle_explosion_anim).apply {
            duration = 700 // milisecond
            interpolator = AccelerateDecelerateInterpolator() // speed of animation not a linear speed one
        }

        tasarim.fabSepet.setOnClickListener {
            tasarim.circle.isVisible = true

            tasarim.circle.startAnimation(animation) {
                //go to sepet fragment
                val bundle = bundleOf("user_id" to user_id, "email_id" to email_id)
                tasarim.navHostFragment.findNavController().navigate(R.id.sepetFragment,bundle)
                tasarim.circle.isVisible = false



            }
        }
    }

    fun View.startAnimation(animation: Animation, onEnd: () -> Unit){ //
        animation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(p0: Animation?) = Unit

            override fun onAnimationEnd(p0: Animation?) {
                onEnd()
            }

            override fun onAnimationRepeat(p0: Animation?) = Unit
        })

        this.startAnimation(animation)
    }


}