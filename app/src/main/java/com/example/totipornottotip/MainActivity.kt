package com.example.totipornottotip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.totipornottotip.databinding.ActivityMainBinding
import com.google.android.material.radiobutton.MaterialRadioButton
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val editText = binding.costOfService
        val textView = binding.tipResult

        binding.button.setOnClickListener {
            var price = editText.text.toString()
            val cost = (price.ifBlank { 0 }).toString().toDouble()
            val tips =  roundTips(radioGroupCalculation(cost))
            textView.text = "Чаевые: $tips"
        }

    }

    private fun radioGroupCalculation(cost: Double): Double {
        val tipOptions = binding.tipOptions
        val checkedRadioButton = tipOptions.checkedRadioButtonId
        val tip = findViewById<MaterialRadioButton>(checkedRadioButton)
        var result = 0.0
        when {
            tip == binding.zeroPercent -> result = calculateTip(cost, 0.0)
            tip == binding.optionTenPercent -> result = calculateTip(cost, 10.0)
            tip == binding.optionFifteenPercent -> result = calculateTip(cost, 15.0)
            tip == binding.optionTwentyPercent -> result = calculateTip(cost, 20.0)
        }
//        tipOptions.setOnCheckedChangeListener { _, checkedId ->
//            when (findViewById<RadioButton>(checkedId)) {
//                binding.zeroPercent -> result = calculateTip(cost, 0.0)
//                binding.optionTenPercent -> result = calculateTip(cost, 10.0)
//                binding.optionFifteenPercent -> result = calculateTip(cost, 15.0)
//                binding.optionTwentyPercent -> result = calculateTip(cost, 20.0) }}
        return result
    }

    private fun roundTips(tip: Double): Double {
        val roundSwitch = binding.roundUpSwitch

//        CompoundButton.OnCheckedChangeListener { _, isChecked ->
//            if (isChecked) {
//                tip.roundToLong()
//            } else {
//                tip.toLong()
//            }
//        }
        return if (roundSwitch.isChecked) (tip.roundToInt()).toDouble() else tip
    }

    private fun calculateTip(cost: Double, percent: Double): Double {
        return cost * percent / 100
    }
}