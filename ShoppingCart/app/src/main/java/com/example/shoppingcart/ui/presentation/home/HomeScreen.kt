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
    cartViewModel: CartViewModel = viewModel(), // CartViewModel injetado
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
                    selected = true,
                    onClick = { navController.navigate("cart/$userId") },
                    label = { Text("Cart") },
                    icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Cart") }
                )
                NavigationBarItem(
                    selected = true,
                    onClick = { navController.navigate("login") },
                    label = { Text("Logout") },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Logout") }
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
                    text = "Bem Vindo $userId",
                    textAlign = TextAlign.Start,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif,
                    color = Color(0xFF37474F)
                )
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(productList.value) { productItem ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp),
                        elevation = CardDefaults.cardElevation(4.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            productItem.imageUrl?.let {
                                AsyncImage(
                                    model = it,
                                    contentDescription = "Image of ${productItem.name}",
                                    modifier = Modifier
                                        .size(80.dp)
                                        .clip(RoundedCornerShape(8.dp)),
                                    contentScale = ContentScale.Crop
                                )
                            }

                            Spacer(modifier = Modifier.width(16.dp))

                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    text = productItem.name,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp,
                                    color = Color(0xFF37474F)
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Preço: ${productItem.price}€",
                                    fontSize = 16.sp,
                                    color = Color(0xFF757575)
                                )
                            }

                            // Botão de adicionar com o ícone de '+'
                            IconButton(
                                onClick = {
                                    // Ação para adicionar o produto ao carrinho
                                    userId?.let {
                                        cartViewModel.addToCart(it, productItem, 1)
                                    }
                                },
                                modifier = Modifier.align(Alignment.CenterVertically)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = "Add to Cart",
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









