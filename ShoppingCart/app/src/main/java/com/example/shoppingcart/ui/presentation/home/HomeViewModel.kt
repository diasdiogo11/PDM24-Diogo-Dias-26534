package com.example.shoppingcart.ui.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingcart.data.repository.ProductRepositoryImpl
import com.example.shoppingcart.data.repository.RegisterRepositoryImpl
import com.example.shoppingcart.domain.model.Product
import com.example.shoppingcart.domain.repository.RegisterRepository
import com.example.shoppingcart.domain.use_case.ProductUseCase
import com.example.shoppingcart.domain.use_case.RegisterUseCase
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


class HomeViewModel : ViewModel() {
    private val repository = ProductRepositoryImpl()
    private val getProductsUseCase = ProductUseCase(repository)
    private val repositoryUser = RegisterRepositoryImpl()
    private val getUserUseCase = RegisterUseCase(repositoryUser)

    private val _productList = MutableStateFlow<List<Product>>(emptyList())
    val productList: StateFlow<List<Product>> get() = _productList

    fun getProductsList() {
        viewModelScope.launch {
            try {
                getProductsUseCase.invoke().collect { products ->
                    _productList.value = products
                }
            } catch (e: Exception) {
                _productList.value = emptyList()
            }
        }
    }

    // Função para atualizar a visibilidade de um produto
    // No seu repositório (ProductRepositoryImpl)
    suspend fun updateVisibility(productId: String, isVisible: Boolean) {
        try {
            val db = Firebase.firestore.collection("Utilizadores").document(productId)

            // Atualizando a visibilidade do produto no Firestore
            db.update("isVisible", isVisible).await()
        } catch (e: Exception) {
            throw e
        }
    }
}
