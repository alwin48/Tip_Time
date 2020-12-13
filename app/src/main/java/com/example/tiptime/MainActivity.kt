package com.example.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalculate.setOnClickListener {
            Toast.makeText(this,"Binding View",Toast.LENGTH_SHORT).show()
            calculateTip()
        }
    }

    private fun calculateTip() {
        val cost : Double? = binding.txtCost.text.toString().toDoubleOrNull()
        if (cost==null) {
            binding.txtTotal.text = ""
            return
        }
        val tipPercentage = when (binding.radioGroup.checkedRadioButtonId) {
            R.id.rbtnAmazing -> 0.20
            R.id.rbtnGood -> 0.18
            R.id.rbtnOkay -> 0.15
            else -> 0.12
        }
        var tip = tipPercentage * cost
        val roundUp : Boolean = binding.switchRoundOff.isChecked
        if(roundUp) {
            tip = ceil(tip)
        }
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.txtTotal.text = formattedTip
    }
}