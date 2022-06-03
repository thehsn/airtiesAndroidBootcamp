package com.example.fooddeliveryapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import com.example.fooddeliveryapp.R
import com.google.firebase.auth.FirebaseAuth

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // making fullscrean--------
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        //---------------------------

        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            val user = FirebaseAuth.getInstance().currentUser

            if(user != null){ // if user signed in
                val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
                intent.flags =
                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                intent.putExtra("user_id", user!!.uid)
                intent.putExtra("email_id", user!!.email)
                startActivity(intent)
                finish()
            }else{
                val intent = Intent(this@SplashScreenActivity, SigninActivity::class.java)
                startActivity(intent)
                finish()
            }

        },3000)
    }
}