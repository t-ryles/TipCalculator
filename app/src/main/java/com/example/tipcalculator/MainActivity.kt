package com.example.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener { calculateTip() }
    }

    fun calculateTip(){
        //Getting the user input cost of service
        val stringInTextField = binding.costOfService.text.toString()

        //Converting that input in a double for calculations
        val cost = stringInTextField.toDouble()

        //Getting the ID of the checked radio button
        val selectedId = binding.tipOptions.checkedRadioButtonId

        //Setting the tip percentage for later calculations
        val tipPercentage = when(selectedId) {
            R.id.option_twenty_percent -> .20
            R.id.option_eighteen_percent -> .18
            else -> .15
        }

        //Calculating the tip
        var tip = tipPercentage * cost

        //Getting the option to round up
        val roundUp = binding.roundUpSwitch.isChecked
        //Rounding tip up is true
        if (roundUp) {
            tip = kotlin.math.ceil(tip)
        }

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)

    }
}