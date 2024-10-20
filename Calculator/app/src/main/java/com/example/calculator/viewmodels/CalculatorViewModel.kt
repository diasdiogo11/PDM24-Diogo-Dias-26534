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

    fun add(){
        CalculatorBrain.add()
    }

    fun subtract(){
        CalculatorBrain.subtract()
    }

    fun multiply(){
        CalculatorBrain.multiply()
    }

    fun divide(){
        CalculatorBrain.divide()
    }

    fun calculate(){
        CalculatorBrain.calculate()
    }

}