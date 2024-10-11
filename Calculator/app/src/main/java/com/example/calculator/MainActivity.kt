package com.example.calculator

import android.inputmethodservice.ExtractEditText
import android.inputmethodservice.Keyboard
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculator.ui.theme.CalculatorTheme
import androidx.compose.runtime.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculatorTheme {
                Column(Modifier
                    .systemBarsPadding()
                    .fillMaxSize()
                    .padding(10.dp),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.End
                ) {
                    Spacer(modifier = Modifier.height(5.dp))
                    CalculatorKeyboard()
                }

            }
        }
    }
}

@Composable
fun CalculatorDisplay(text: String){
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.End) {
        Text(
            text = text,
            fontSize = 60.sp
        )
    }
}

@Composable
fun CalculatorButton(text: String, color: Color, onClick: () -> Unit, ){
    Button(
        onClick  = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 30.dp))
            .size(75.dp),
            colors = ButtonDefaults.buttonColors(containerColor = color),
    ) {
        Text(text = text, fontSize = 20.sp, textAlign = TextAlign.Center, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun CalculatorKeyboard(){
    var displayText by remember { mutableStateOf("0") }
    var numberOne by remember { mutableStateOf("") }
    var numberTwo by remember { mutableStateOf("") }
    Column(modifier = Modifier
        .fillMaxWidth()
    ) {
        CalculatorDisplay(text = displayText)
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
                CalculatorButton("ON/C", Color.Red) { displayText = "0"}
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
                CalculatorButton("7", Color.Gray) {displayText = if (displayText == "0") "7" else displayText + "7"}
            }
            Spacer(modifier = Modifier.width(5.dp))
            Column(Modifier.weight(1f)) {
                CalculatorButton("8", Color.Gray) {displayText = if (displayText == "0") "8" else displayText + "8" }
            }
            Spacer(modifier = Modifier.width(5.dp))
            Column(Modifier.weight(1f)) {
                CalculatorButton("9", Color.Gray) {displayText = if (displayText == "0") "9" else displayText + "9" }
            }
            Spacer(modifier = Modifier.width(5.dp))
            Column(Modifier.weight(1f)) {
                CalculatorButton("÷", Color.Black) { }
            }
        }
        Spacer(modifier = Modifier.height(5.dp))
        Row {
            Column(Modifier.weight(1f)) {
                CalculatorButton("4", Color.Gray) {displayText = if (displayText == "0") "4" else displayText + "4" }
            }
            Spacer(modifier = Modifier.width(5.dp))
            Column(Modifier.weight(1f)) {
                CalculatorButton("5", Color.Gray) { displayText = if (displayText == "0") "5" else displayText + "5" }
            }
            Spacer(modifier = Modifier.width(5.dp))
            Column(Modifier.weight(1f)) {
                CalculatorButton("6", Color.Gray) {displayText = if (displayText == "0") "6" else displayText + "6" }
            }
            Spacer(modifier = Modifier.width(5.dp))
            Column(Modifier.weight(1f)) {
                CalculatorButton("×", Color.Black) { }
            }
        }
        Spacer(modifier = Modifier.height(5.dp))
        Row {
            Column(Modifier.weight(1f)) {
                CalculatorButton("1", Color.Gray) {displayText = if (displayText == "0") "1" else displayText + "1" }
            }
            Spacer(modifier = Modifier.width(5.dp))
            Column(Modifier.weight(1f)) {
                CalculatorButton("2", Color.Gray) {displayText = if (displayText == "0") "2" else displayText + "2" }
            }
            Spacer(modifier = Modifier.width(5.dp))
            Column(Modifier.weight(1f)) {
                CalculatorButton("3", Color.Gray) {displayText = if (displayText == "0") "3" else displayText + "3"}
            }
            Spacer(modifier = Modifier.width(5.dp))
            Column(Modifier.weight(1f)) {
                CalculatorButton("-", Color.Black) { }
            }
        }
        Spacer(modifier = Modifier.height(5.dp))
        Row {
            Column(Modifier.weight(1f)) {
                CalculatorButton("0", Color.Gray) { displayText = if (displayText == "0") "0" else displayText + "0"}
            }
            Spacer(modifier = Modifier.width(5.dp))
            Column(Modifier.weight(1f)) {
                CalculatorButton(".", Color.Gray) {displayText = if (displayText == "0") "." else displayText + "." }
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CalculatorTheme {
        CalculatorDisplay("0")
        CalculatorKeyboard()
    }
}