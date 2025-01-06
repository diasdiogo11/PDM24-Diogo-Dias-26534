package com.example.shoppingcart.ui.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingcart.data.repository.ProductRepositoryImpl
import com.example.shoppingcart.domain.model.Product
import com.example.shoppingcart.domain.use_case.ProductUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class HomeViewModel: ViewModel() {
    private val repository = ProductRepositoryImpl()
    private val getProductsUseCase = ProductUseCase(repository)

    private val _productList = MutableStateFlow<List<Product>>(emptyList())
    val productList: StateFlow<List<Product>> get() = _productList

    fun getProductsList(){
        viewModelScope.launch{
            try{
                getProductsUseCase.invoke().collect { products ->
                    _productList.value = products
                }
            }
            catch(e: Exception){
                _productList.value = emptyList()
            }
        }
    }
}
