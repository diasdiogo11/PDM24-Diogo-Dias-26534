package com.example.calculator.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object CalculatorBrain {

    var currentValue by mutableStateOf("0")
    var storedValue by mutableStateOf("0")
    var currentOperation: String? = null

    fun enterNumber(number: String) {
        if (currentValue == "0") {
            currentValue = number
        } else {
            currentValue += number
        }
    }

    fun clear() {
        currentValue = "0"
        storedValue = "0"
        currentOperation = null
    }

}