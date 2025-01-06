package com.example.shoppingcart.domain.repository

import kotlinx.coroutines.flow.Flow

interface RegisterRepository {
    suspend fun register(email: String, password: String) : Flow<Boolean>

    suspend fun createUser(id: String, name: String, email: String)
}