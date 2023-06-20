package com.example.bmiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.bmiapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val weightText = binding.etWeight
        val heightText = binding.etHeight
        val calcButton = binding.btnCalculate

        calcButton.setOnClickListener {
            val weight = weightText.text.trim().toString()
            val height = heightText.text.trim().toString()

            if (validateInput(weight,height)) {


                val bmi = weight.toFloat() / ((height.toFloat() / 100) * (height.toFloat() / 100))

                //get result with 2 digits
                val bmi2Digits = String.format("%.2f", bmi).toFloat()
                displayResult(bmi2Digits)
            }
        }
    }

    private fun validateInput(weight:String?, height:String?):Boolean{
        return when{
            weight.isNullOrEmpty() ->{
                Toast.makeText(this, "Weight is empty", Toast.LENGTH_SHORT).show()
                return false
            }
            height.isNullOrEmpty() ->{
                Toast.makeText(this, "height is empty", Toast.LENGTH_SHORT).show()
                return false
            }

            else -> {
                return true
            }
        }

    }

    private fun displayResult(bmi: Float){
        val resultIndex = findViewById<TextView>(R.id.tvIndex)
        val resultDescription = findViewById<TextView>(R.id.tvResult)
        val info = findViewById<TextView>(R.id.tvInfo)

        resultIndex.text = bmi.toString()
        info.text = "(Normal range is 18.5-24.9)"

        var resultText = ""
        var color = 0

        when{
            bmi<18.5 ->{

                resultText = "You're under weight"
                color = R.color.under_weight
            }
            bmi in 18.5..24.9 ->{

                resultText = "You're Healthy"
                color = R.color.normal
            }
            bmi in 25.00..29.99 ->{
                resultText = "You're over weight"
                color = R.color.over_weight
            }
            bmi>29.99->{
                resultText = "Obese"
                color = R.color.obese
            }
        }


        resultDescription.setTextColor(ContextCompat.getColor(this, color))
        resultDescription.text = resultText
    }
}