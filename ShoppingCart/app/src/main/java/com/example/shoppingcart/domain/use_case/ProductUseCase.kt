package com.example.shoppingcart.domain.use_case

import com.example.shoppingcart.domain.model.Product
import com.example.shoppingcart.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow


class ProductUseCase(private val repository: ProductRepository) {
    suspend operator fun invoke() : Flow<List<Product>> {
        return repository.getProducts()
    }
}