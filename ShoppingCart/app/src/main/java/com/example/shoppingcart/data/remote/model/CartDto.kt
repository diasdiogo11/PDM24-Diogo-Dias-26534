package com.example.shoppingcart.data.remote.model

import com.example.shoppingcart.domain.model.Cart

data class CartDto(
    val userId: String,
    val items: List<CartItemDto>,
    val totalItems: Int,
    val totalPrice: Double
) {
    fun toCart(): Cart {
        return Cart(
            userId = userId,
            products = items.map { it.toCartItem() },
            totalItems = totalItems,
            totalPrice = totalPrice
        )
    }
}