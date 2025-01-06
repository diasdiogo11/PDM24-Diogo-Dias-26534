package com.example.shoppingcart.domain.repository

import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    suspend fun login(email: String, password: String) : Flow<Boolean>

    suspend fun getLoggedUserId(): String
}