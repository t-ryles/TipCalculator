package com.example.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener { calculateTip() }
    }

    private fun displayTip(tip: Double) {
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }

    private fun calculateTip(){
        //Getting the user input cost of service
        val stringInTextField = binding.costOfService.text.toString()

        //Converting that input in a double for calculations
        val cost = stringInTextField.toDoubleOrNull()
            if (cost == null || cost == 0.00) {
                displayTip(0.00)
                return
            }

        //Getting the ID of the checked radio button
        //Setting the tip percentage for later calculations
        val tipPercentage = when(binding.tipOptions.checkedRadioButtonId) {
            R.id.option_twenty_percent -> .20
            R.id.option_eighteen_percent -> .18
            else -> .15
        }

        //Calculating the tip
        var tip = tipPercentage * cost

        //Getting the option to round up
        //Rounding tip up is true
        if (binding.roundUpSwitch.isChecked) {
            tip = kotlin.math.ceil(tip)
        }

        displayTip(tip)

    }
}