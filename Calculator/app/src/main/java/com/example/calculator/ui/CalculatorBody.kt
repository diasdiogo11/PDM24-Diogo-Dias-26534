package com.example.calculator.ui

import androidx.compose.runtime.Composable
import com.example.calculator.models.CalculatorBrain
import com.example.calculator.viewmodels.CalculatorViewModel


@Composable
fun CalculatorBody(){

    CalculatorScreen(text = CalculatorBrain.currentValue)
    CalculatorKeyboard()
}