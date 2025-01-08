package com.example.shoppingcart.ui.presentation.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.shoppingcart.domain.model.CartItem

@Composable
fun CartScreen(
    navController: NavController,
    userId: String,
    cartViewModel: CartViewModel = viewModel() // CartViewModel injetado
) {
    val cartState by cartViewModel.cart.collectAsState()

    // Obtenção do carrinho do usuário
    LaunchedEffect(userId) {
        cartViewModel.getCart(userId)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = true,
                    onClick = { navController.navigate("cart/$userId") },
                    label = { Text("Cart") },
                    icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Cart") }
                )
                NavigationBarItem(
                    selected = true,
                    onClick = { navController.navigate("home/$userId") },
                    label = { Text("Home") },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") }
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color(0xFFB3E5FC), Color(0xFFE1F5FE))
                    )
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Carrinho de $userId",
                    textAlign = TextAlign.Start,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif,
                    color = Color(0xFF37474F)
                )
            }

            // Exibir o carrinho do usuário
            cartState?.let { cart ->
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(cart.products) { cartItem ->
                        CartItemView(cartItem = cartItem)
                    }

                    item {
                        Text(
                            text = "Total: ${cart.totalPrice}€",
                            modifier = Modifier.padding(16.dp),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF37474F)
                        )
                    }
                }
            } ?: run {
                // Exibir mensagem caso não haja itens no carrinho
                Text(
                    text = "Carrinho vazio",
                    modifier = Modifier.fillMaxSize(),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun CartItemView(cartItem: CartItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = cartItem.product.name,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF37474F)
        )
        Text(
            text = "x${cartItem.quantity}",
            fontSize = 16.sp,
            color = Color(0xFF37474F)
        )
    }
}
