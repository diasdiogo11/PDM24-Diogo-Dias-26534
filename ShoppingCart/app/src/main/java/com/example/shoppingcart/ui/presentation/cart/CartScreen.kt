package com.example.shoppingcart.ui.presentation.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
                    selected = false,
                    onClick = { navController.navigate("cart/$userId") },
                    label = { Text("Carrinho") },
                    icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Carrinho") }
                )
                NavigationBarItem(
                    selected = false,
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
            // Cabeçalho com o nome do usuário
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Carrinho de $userId",
                    textAlign = TextAlign.Start,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif,
                    color = Color(0xFF37474F)
                )
            }

            // Exibir o carrinho do usuário
            cartState?.let { cart ->
                if (cart.products.isNotEmpty()) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = 80.dp) // Espaço para o rodapé
                    ) {
                        items(cart.products) { cartItem ->
                            CartItemView(
                                cartItem = cartItem,
                                cartViewModel = cartViewModel, // Passar o ViewModel
                                userId = userId // Passar o userId
                            )
                        }

                        item {
                            // Exibir o total
                            Text(
                                text = "Total: ${cart.totalPrice}€",
                                modifier = Modifier
                                    .padding(horizontal = 16.dp, vertical = 8.dp),
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF37474F)
                            )
                        }
                    }
                } else {
                    // Exibir mensagem de carrinho vazio
                    Text(
                        text = "Carrinho vazio",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}

@Composable
fun CartItemView(cartItem: CartItem, cartViewModel: CartViewModel, userId: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Nome do produto
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = cartItem.product.name,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF37474F)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Preço: ${cartItem.product.price}€",
                fontSize = 16.sp,
                color = Color(0xFF757575)
            )
        }

        // Quantidade e ícone de remover
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = "x${cartItem.quantity}",
                fontSize = 16.sp,
                color = Color(0xFF37474F)
            )

            Spacer(modifier = Modifier.width(8.dp))

            IconButton(
                onClick = {
                    // Ação para remover o item do carrinho
                    cartViewModel.removeFromCart(userId, cartItem.product.id) // Passar o userId
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Remover do carrinho",
                    tint = Color(0xFF37474F)
                )
            }
        }
    }
}



