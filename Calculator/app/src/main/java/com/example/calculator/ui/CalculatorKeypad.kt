package com.example.calculator.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.calculator.models.CalculatorBrain
import com.example.calculator.viewmodels.CalculatorViewModel

@Composable
fun CalculatorKeyboard(viewModel: CalculatorViewModel){
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
                CalculatorButton("ON/C", Color.Red) {viewModel.clearCalculator()}
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
                CalculatorButton("7", Color.Gray) { viewModel.enterNumber("7")}
            }
            Spacer(modifier = Modifier.width(5.dp))
            Column(Modifier.weight(1f)) {
                CalculatorButton("8", Color.Gray) { viewModel.enterNumber("8")}
            }
            Spacer(modifier = Modifier.width(5.dp))
            Column(Modifier.weight(1f)) {
                CalculatorButton("9", Color.Gray) {viewModel.enterNumber("9")}
            }
            Spacer(modifier = Modifier.width(5.dp))
            Column(Modifier.weight(1f)) {
                CalculatorButton("÷", Color.Black) {viewModel.div() }
            }
        }
        Spacer(modifier = Modifier.height(5.dp))
        Row {
            Column(Modifier.weight(1f)) {
                CalculatorButton("4", Color.Gray) { viewModel.enterNumber("4")}
            }
            Spacer(modifier = Modifier.width(5.dp))
            Column(Modifier.weight(1f)) {
                CalculatorButton("5", Color.Gray) {viewModel.enterNumber("5")  }
            }
            Spacer(modifier = Modifier.width(5.dp))
            Column(Modifier.weight(1f)) {
                CalculatorButton("6", Color.Gray) {viewModel.enterNumber("6") }
            }
            Spacer(modifier = Modifier.width(5.dp))
            Column(Modifier.weight(1f)) {
                CalculatorButton("×", Color.Black) {viewModel.mult() }
            }
        }
        Spacer(modifier = Modifier.height(5.dp))
        Row {
            Column(Modifier.weight(1f)) {
                CalculatorButton("1", Color.Gray) {viewModel.enterNumber("1") }
            }
            Spacer(modifier = Modifier.width(5.dp))
            Column(Modifier.weight(1f)) {
                CalculatorButton("2", Color.Gray) {viewModel.enterNumber("2")}
            }
            Spacer(modifier = Modifier.width(5.dp))
            Column(Modifier.weight(1f)) {
                CalculatorButton("3", Color.Gray) {viewModel.enterNumber("3")}
            }
            Spacer(modifier = Modifier.width(5.dp))
            Column(Modifier.weight(1f)) {
                CalculatorButton("-", Color.Black) {viewModel.sub() }
            }
        }
        Spacer(modifier = Modifier.height(5.dp))
        Row {
            Column(Modifier.weight(1f)) {
                CalculatorButton("0", Color.Gray) {viewModel.enterNumber("0") }
            }
            Spacer(modifier = Modifier.width(5.dp))
            Column(Modifier.weight(1f)) {
                CalculatorButton(".", Color.Gray) {viewModel.enterNumber(".") }
            }
            Spacer(modifier = Modifier.width(5.dp))
            Column(Modifier.weight(1f)) {
                CalculatorButton("=", Color.Gray) {viewModel.calculate() }
            }
            Spacer(modifier = Modifier.width(5.dp))
            Column(Modifier.weight(1f)) {
                CalculatorButton("+", Color.Black) {viewModel.add() }
            }
        }
    }
}