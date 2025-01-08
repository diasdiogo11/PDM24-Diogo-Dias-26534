package com.example.shoppingcart.ui.presentation.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingcart.domain.model.Cart
import com.example.shoppingcart.domain.use_case.CartUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CartViewModel(private val cartUseCase: CartUseCase) : ViewModel() {
    private val _cart = MutableStateFlow<Cart?>(null)
    val cart: StateFlow<Cart?> get() = _cart

    fun getCart(userId: String) {
        viewModelScope.launch {
            try {
                cartUseCase(userId).collect { cart ->
                    _cart.value = cart
                }
            } catch (e: Exception) {
                _cart.value = null
            }
        }
    }
}