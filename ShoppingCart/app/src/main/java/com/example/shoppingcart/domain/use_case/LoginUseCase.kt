package com.example.shoppingcart.domain.use_case

import com.example.shoppingcart.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow

class LoginUseCase(private val repository: LoginRepository) {
    suspend operator fun invoke(email: String, password: String): Flow<Boolean> {
        return repository.login(email = email, password = password)
    }

    suspend fun getLoggedUserId(): String{
        return repository.getLoggedUserId()
    }
}