package com.example.shoppingcart.domain.repository

import com.example.shoppingcart.domain.model.Cart
import com.example.shoppingcart.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    suspend fun getCart(userId: String): Flow<Cart>
    suspend fun addToCart(userId: String, product: Product, quantity: Int)
    suspend fun removeFromCart(userId: String, productId: String)
}