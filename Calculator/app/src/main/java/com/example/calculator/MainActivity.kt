package com.example.calculator

import android.inputmethodservice.ExtractEditText
import android.inputmethodservice.Keyboard
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calculator.ui.theme.CalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculatorTheme {
                Column(Modifier.systemBarsPadding()) {
                    CalculatorKeyboard()
                }

            }
        }
    }
}

@Composable
fun CalculatorDisplay(text: String){
    Text(text = text)
}

@Composable
fun CalculatorButton(text: String, onClick: () -> Unit, ){
    Button(
        onClick  = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)

    ) {
        Text(text = text)
    }
}

@Composable
fun CalculatorKeyboard(){
    Column(modifier = Modifier.fillMaxWidth()) {
        Row {
            Column(Modifier.weight(1f)) {
                CalculatorButton("MRC") { }
            }
            Column(Modifier.weight(1f)) {
                CalculatorButton("M-") { }
            }
            Column(Modifier.weight(1f)) {
                CalculatorButton("M+") { }
            }
            Column(Modifier.weight(1f)) {
                CalculatorButton("ON/C") { }
            }
        }
        Row {
            Column(Modifier.weight(1f)) {
                CalculatorButton("√") { }
            }
            Column(Modifier.weight(1f)) {
                CalculatorButton("%") { }
            }
            Column(Modifier.weight(1f)) {
                CalculatorButton("+/-") { }
            }
            Column(Modifier.weight(1f)) {
                CalculatorButton("CE") { }
            }
        }
        Row {
            Column(Modifier.weight(1f)) {
                CalculatorButton("7") { }
            }
            Column(Modifier.weight(1f)) {
                CalculatorButton("8") { }
            }
            Column(Modifier.weight(1f)) {
                CalculatorButton("9") { }
            }
            Column(Modifier.weight(1f)) {
                CalculatorButton("÷") { }
            }
        }
        Row {
            Column(Modifier.weight(1f)) {
                CalculatorButton("4") { }
            }
            Column(Modifier.weight(1f)) {
                CalculatorButton("5") { }
            }
            Column(Modifier.weight(1f)) {
                CalculatorButton("6") { }
            }
            Column(Modifier.weight(1f)) {
                CalculatorButton("×") { }
            }
        }
        Row {
            Column(Modifier.weight(1f)) {
                CalculatorButton("1") { }
            }
            Column(Modifier.weight(1f)) {
                CalculatorButton("2") { }
            }
            Column(Modifier.weight(1f)) {
                CalculatorButton("3") { }
            }
            Column(Modifier.weight(1f)) {
                CalculatorButton("-") { }
            }
        }
        Row {
            Column(Modifier.weight(1f)) {
                CalculatorButton("0") { }
            }
            Column(Modifier.weight(1f)) {
                CalculatorButton(".") { }
            }
            Column(Modifier.weight(1f)) {
                CalculatorButton("=") { }
            }
            Column(Modifier.weight(1f)) {
                CalculatorButton("+") { }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CalculatorTheme {
        CalculatorDisplay("dois")
        CalculatorKeyboard()
    }
}