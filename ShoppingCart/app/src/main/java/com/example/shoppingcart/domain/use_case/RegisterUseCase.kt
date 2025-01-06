package com.example.shoppingcart.domain.use_case

import com.example.shoppingcart.domain.repository.RegisterRepository
import kotlinx.coroutines.flow.Flow

class RegisterUseCase(private val repository: RegisterRepository) {
    suspend operator fun invoke(email: String, password: String): Flow<Boolean> {
        return repository.register(email = email, password = password)
    }

    suspend fun createUser(id: String, name: String, email: String) {
        return repository.createUser(id = id, name = name, email = email)
    }
}