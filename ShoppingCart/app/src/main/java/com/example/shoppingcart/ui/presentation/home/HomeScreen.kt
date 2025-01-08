package com.example.shoppingcart.ui.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.shoppingcart.ui.presentation.cart.CartViewModel

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = viewModel(),
    cartViewModel: CartViewModel = viewModel(),
    navController: NavController,
    userId: String?
) {
    val productList = homeViewModel.productList.collectAsState()

    LaunchedEffect(Unit) {
        homeViewModel.getProductsList()
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
                    onClick = { navController.navigate("login") },
                    label = { Text("Logout") },
                    icon = { Icon(Icons.Default.ExitToApp, contentDescription = "Logout") }
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
                    text = "Bem-vindo, $userId",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF37474F),
                    modifier = Modifier.weight(1f)
                )
            }

            // Lista de produtos
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(productList.value) { productItem ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp)
                            .clip(RoundedCornerShape(12.dp)),
                        elevation = CardDefaults.cardElevation(8.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Imagem do produto
                            productItem.imageUrl?.let {
                                AsyncImage(
                                    model = it,
                                    contentDescription = "Imagem de ${productItem.name}",
                                    modifier = Modifier
                                        .size(80.dp)
                                        .clip(RoundedCornerShape(8.dp)),
                                    contentScale = ContentScale.Crop
                                )
                            }

                            Spacer(modifier = Modifier.width(16.dp))

                            // Descrição do produto
                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    text = productItem.name,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp,
                                    color = Color(0xFF37474F)
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Preço: ${productItem.price}€",
                                    fontSize = 16.sp,
                                    color = Color(0xFF757575)
                                )
                            }

                            // Botão de adicionar ao carrinho
                            IconButton(
                                onClick = {
                                    userId?.let {
                                        cartViewModel.addToCart(it, productItem, 1)
                                    }
                                },
                                modifier = Modifier.align(Alignment.CenterVertically)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = "Adicionar ao Carrinho",
                                    tint = Color(0xFF37474F)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}










