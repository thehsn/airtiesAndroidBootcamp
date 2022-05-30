package com.example.fooddeliveryapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.fooddeliveryapp.databinding.ActivitySigninBinding
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase

class SigninActivity : AppCompatActivity() {


    companion object{
        private const val RC_SIGN_IN = 120
    }

    private lateinit var tasarim:ActivitySigninBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tasarim = ActivitySigninBinding.inflate(layoutInflater)

        setContentView(tasarim.root)


        tasarim.textViewRegister.setOnClickListener {
            val intent = Intent(this@SigninActivity, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

        tasarim.buttonSignIn.setOnClickListener {

            when {//we check whether user entered a password and email
                TextUtils.isEmpty(
                    tasarim.editTextEmail.text.toString()
                        .trim { it <= ' ' }) -> { //trims empty spaces
                    Toast.makeText(this@SigninActivity, "Enter your email", Toast.LENGTH_SHORT)
                        .show()
                }
                TextUtils.isEmpty(tasarim.editTextPassword.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(this@SigninActivity, "Enter your password", Toast.LENGTH_SHORT)
                        .show()
                }

                else -> { // if none of those happen

                    //if user accidently enters a white space we trim it
                    val email = tasarim.editTextEmail.text.toString().trim(' ')
                    val password = tasarim.editTextPassword.text.toString().trim(' ')

                    //create an instance and create a register a user with email and password
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener({ task ->

                            //if registration is successfully done
                            if (task.isSuccessful) {

                                //we send user to main screen with user id and email
                                val intent = Intent(this@SigninActivity, MainActivity::class.java)
                                intent.flags =
                                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                intent.putExtra("user_id", FirebaseAuth.getInstance().currentUser!!.uid)
                                intent.putExtra("email_id", email)
                                startActivity(intent)
                                finish()
                            } else {
                                //registration is not successful
                                Toast.makeText(
                                    this@SigninActivity, task.exception!!.message.toString(),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                        })


                }

            }
        }
    }

}