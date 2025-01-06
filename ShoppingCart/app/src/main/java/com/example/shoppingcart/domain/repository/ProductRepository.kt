package com.example.shoppingcart.domain.repository

import com.example.shoppingcart.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun getProducts(): Flow<List<Product>>
}