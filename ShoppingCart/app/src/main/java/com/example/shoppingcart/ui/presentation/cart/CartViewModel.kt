package com.example.shoppingcart.ui.presentation.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingcart.data.repository.CartRepositoryImpl
import com.example.shoppingcart.domain.model.Cart
import com.example.shoppingcart.domain.model.Product
import com.example.shoppingcart.domain.use_case.CartUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CartViewModel : ViewModel() {
    private val repository = CartRepositoryImpl()
    private val cartUseCase = CartUseCase(repository)

    private val _cart = MutableStateFlow<Cart?>(null)
    val cart: StateFlow<Cart?> get() = _cart

    private val _addToCartStatus = MutableStateFlow<Boolean?>(null)
    val addToCartStatus: StateFlow<Boolean?> get() = _addToCartStatus

    private val _removeFromCartStatus = MutableStateFlow<Boolean?>(null)
    val removeFromCartStatus: StateFlow<Boolean?> get() = _removeFromCartStatus

    fun getCart(userId: String) {
        viewModelScope.launch {
            try {
                cartUseCase.invoke(userId).collect { cartData ->
                    _cart.value = cartData
                }
            } catch (e: Exception) {
                _cart.value = null
            }
        }
    }

    fun addToCart(userId: String, product: Product, quantity: Int) {
        viewModelScope.launch {
            try {
                cartUseCase.addToCart(userId, product, quantity)
                _addToCartStatus.value = true // Indica sucesso
            } catch (e: Exception) {
                _addToCartStatus.value = false // Indica falha
            }
        }
    }

    // Função para remover item do carrinho
    fun removeFromCart(userId: String, productId: String) {
        viewModelScope.launch {
            try {
                cartUseCase.removeFromCart(userId, productId)
                _removeFromCartStatus.value = true // Indica sucesso
            } catch (e: Exception) {
                _removeFromCartStatus.value = false // Indica falha
            }
        }
    }
}

