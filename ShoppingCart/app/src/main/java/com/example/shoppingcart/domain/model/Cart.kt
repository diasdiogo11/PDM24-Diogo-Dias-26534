package com.example.shoppingcart.domain.model

data class Cart(
    val userId: String,
    val products: List<CartItem>,
    val totalItems: Int = products.sumOf { it.quantity },
    val totalPrice: Double = products.sumOf { it.quantity * it.product.price }
)