package com.example.shoppingcart.domain.use_case

import com.example.shoppingcart.domain.model.Cart
import com.example.shoppingcart.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow

class CartUseCase(private val cartRepository: CartRepository) {
    suspend operator fun invoke(userId: String): Flow<Cart> {
        return cartRepository.getCart(userId)
    }
}