package com.example.fooddeliveryapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.fooddeliveryapp.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class RegisterActivity : AppCompatActivity() {
    private lateinit var tasarim:ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tasarim = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(tasarim.root)

        tasarim.buttonRegister.setOnClickListener {
            when{//we check whether user entered a password and email
                TextUtils.isEmpty(tasarim.editTextEmail.text.toString().trim { it <= ' ' }) -> { //trims empty spaces
                    Toast.makeText(this@RegisterActivity,"Enter your email", Toast.LENGTH_SHORT).show()
                }
                TextUtils.isEmpty(tasarim.editTextPassword.text.toString().trim { it <= ' ' }) ->{
                    Toast.makeText(this@RegisterActivity,"Enter your password", Toast.LENGTH_SHORT).show()
                }

                else -> { // if none of those happen

                    //if user accidently enters a white space we trim it
                    val email = tasarim.editTextEmail.text.toString().trim(' ')
                    val password = tasarim.editTextPassword.text.toString().trim(' ')

                    //create an instance and create a register a user with email and password
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener({task->

                            //if registration is successfully done
                            if(task.isSuccessful){

                                //firebase registered user
                                val firebaseUser:FirebaseUser = task.result!!.user!!

                                //we send user to main screen with user id and email
                                val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                intent.putExtra("user_id", firebaseUser.uid)
                                intent.putExtra("email_id", email)
                                startActivity(intent)
                                finish()
                            } else{
                                //registration is not successful
                                Toast.makeText(this@RegisterActivity, task.exception!!.message.toString(),Toast.LENGTH_SHORT).show()
                            }

                        })


                }

            }
        }
    }
}