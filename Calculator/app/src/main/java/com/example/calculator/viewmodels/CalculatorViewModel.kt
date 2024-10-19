package com.example.calculator.viewmodels

import androidx.lifecycle.ViewModel
import com.example.calculator.models.CalculatorBrain

class CalculatorViewModel: ViewModel() {

    fun enterNumber(number: String) {
        CalculatorBrain.enterNumber(number)
    }

    fun clearCalculator() {
        CalculatorBrain.clear()
    }

}