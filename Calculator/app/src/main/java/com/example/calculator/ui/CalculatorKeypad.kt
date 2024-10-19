package com.example.calculator.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CalculatorKeyboard(){
    Column(modifier = Modifier
        .fillMaxWidth()
    ) {
        Row {
            Column(Modifier.weight(1f)) {
                CalculatorButton("MRC", Color.Black) { }
            }
            Spacer(modifier = Modifier.width(5.dp))
            Column(Modifier.weight(1f)) {
                CalculatorButton("M-", Color.Black) { }
            }
            Spacer(modifier = Modifier.width(5.dp))
            Column(Modifier.weight(1f)) {
                CalculatorButton("M+", Color.Black) { }
            }
            Spacer(modifier = Modifier.width(5.dp))
            Column(Modifier.weight(1f)) {
                CalculatorButton("ON/C", Color.Red) {}
            }
        }
        Spacer(modifier = Modifier.height(5.dp))
        Row {
            Column(Modifier.weight(1f)) {
                CalculatorButton("√", Color.Black) { }
            }
            Spacer(modifier = Modifier.width(5.dp))
            Column(Modifier.weight(1f)) {
                CalculatorButton("%", Color.Black) { }
            }
            Spacer(modifier = Modifier.width(5.dp))
            Column(Modifier.weight(1f)) {
                CalculatorButton("+/-", Color.Black) { }
            }
            Spacer(modifier = Modifier.width(5.dp))
            Column(Modifier.weight(1f)) {
                CalculatorButton("CE", Color.Red) { }
            }
        }
        Spacer(modifier = Modifier.height(5.dp))
        Row {
            Column(Modifier.weight(1f)) {
                CalculatorButton("7", Color.Gray) {}
            }
            Spacer(modifier = Modifier.width(5.dp))
            Column(Modifier.weight(1f)) {
                CalculatorButton("8", Color.Gray) { }
            }
            Spacer(modifier = Modifier.width(5.dp))
            Column(Modifier.weight(1f)) {
                CalculatorButton("9", Color.Gray) { }
            }
            Spacer(modifier = Modifier.width(5.dp))
            Column(Modifier.weight(1f)) {
                CalculatorButton("÷", Color.Black) { }
            }
        }
        Spacer(modifier = Modifier.height(5.dp))
        Row {
            Column(Modifier.weight(1f)) {
                CalculatorButton("4", Color.Gray) { }
            }
            Spacer(modifier = Modifier.width(5.dp))
            Column(Modifier.weight(1f)) {
                CalculatorButton("5", Color.Gray) {  }
            }
            Spacer(modifier = Modifier.width(5.dp))
            Column(Modifier.weight(1f)) {
                CalculatorButton("6", Color.Gray) { }
            }
            Spacer(modifier = Modifier.width(5.dp))
            Column(Modifier.weight(1f)) {
                CalculatorButton("×", Color.Black) { }
            }
        }
        Spacer(modifier = Modifier.height(5.dp))
        Row {
            Column(Modifier.weight(1f)) {
                CalculatorButton("1", Color.Gray) { }
            }
            Spacer(modifier = Modifier.width(5.dp))
            Column(Modifier.weight(1f)) {
                CalculatorButton("2", Color.Gray) { }
            }
            Spacer(modifier = Modifier.width(5.dp))
            Column(Modifier.weight(1f)) {
                CalculatorButton("3", Color.Gray) { }
            }
            Spacer(modifier = Modifier.width(5.dp))
            Column(Modifier.weight(1f)) {
                CalculatorButton("-", Color.Black) { }
            }
        }
        Spacer(modifier = Modifier.height(5.dp))
        Row {
            Column(Modifier.weight(1f)) {
                CalculatorButton("0", Color.Gray) { }
            }
            Spacer(modifier = Modifier.width(5.dp))
            Column(Modifier.weight(1f)) {
                CalculatorButton(".", Color.Gray) { }
            }
            Spacer(modifier = Modifier.width(5.dp))
            Column(Modifier.weight(1f)) {
                CalculatorButton("=", Color.Gray) { }
            }
            Spacer(modifier = Modifier.width(5.dp))
            Column(Modifier.weight(1f)) {
                CalculatorButton("+", Color.Black) { }
            }
        }
    }
}