package com.example.shoppingcart.domain.model

data class CartItem(
    val product: Product, // Produto no carrinho
    val quantity: Int // Quantidade do produto
)