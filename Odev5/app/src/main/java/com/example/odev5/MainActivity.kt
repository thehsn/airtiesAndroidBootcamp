package com.example.odev5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.odev5.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var tasarim:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tasarim = ActivityMainBinding.inflate(layoutInflater)
        setContentView(tasarim.root)

        var ekran:String = ""

        tasarim.buttonOne.setOnClickListener {
            ekran = ekran + "1"
            tasarim.textViewSonuc.text = ekran
        }

        tasarim.buttonTwo.setOnClickListener {
            ekran = ekran + "2"
            tasarim.textViewSonuc.text = ekran
        }

        tasarim.buttonThree.setOnClickListener {
            ekran = ekran + "3"
            tasarim.textViewSonuc.text = ekran
        }

        tasarim.buttonFour.setOnClickListener {
            ekran = ekran + "4"
            tasarim.textViewSonuc.text = ekran
        }

        tasarim.buttonFive.setOnClickListener {
            ekran = ekran + "5"
            tasarim.textViewSonuc.text = ekran
        }

        tasarim.buttonSix.setOnClickListener {
            ekran = ekran + "6"
            tasarim.textViewSonuc.text = ekran
        }

        tasarim.buttonSeven.setOnClickListener {
            ekran = ekran + "7"
            tasarim.textViewSonuc.text = ekran
        }

        tasarim.buttonEight.setOnClickListener {
            ekran = ekran + "8"
            tasarim.textViewSonuc.text = ekran
        }

        tasarim.buttonNine.setOnClickListener {
            ekran = ekran + "9"
            tasarim.textViewSonuc.text = ekran
        }

        tasarim.buttonZero.setOnClickListener {
            ekran = ekran + "0"
            tasarim.textViewSonuc.text = ekran
        }

        tasarim.buttonPlus.setOnClickListener {
            ekran = "$ekran + "
            tasarim.textViewSonuc.text = ekran
        }

        tasarim.buttonResult.setOnClickListener {

            if(ekran.isEmpty()){
                Log.e("kontrol", "bos")
                Snackbar.make(it, "Please enter a number", Snackbar.LENGTH_SHORT).show()

            }else{
                var result = 0
                val numbers = ekran.split(" + ")
                for (num in numbers){
                    Log.e("kontrol", num)
                    num.trim()
                    result += num.toInt()
                }
                Log.e("kontrol", result.toString())
                tasarim.textViewSonuc.text = result.toString()
                ekran = ""
            }


        }

        tasarim.buttonClear.setOnClickListener {
            ekran = ""
            tasarim.textViewSonuc.text = ekran
        }


    }
}