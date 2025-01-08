package com.example.shoppingcart.domain.repository

import com.example.shoppingcart.domain.model.Cart
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    suspend fun getCart(userId: String): Flow<Cart>
}