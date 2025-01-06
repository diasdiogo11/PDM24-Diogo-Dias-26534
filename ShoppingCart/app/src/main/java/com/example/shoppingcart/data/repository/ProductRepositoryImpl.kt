package com.example.shoppingcart.data.repository

import com.example.shoppingcart.data.remote.model.ProductDto
import com.example.shoppingcart.domain.model.Product
import com.example.shoppingcart.domain.repository.ProductRepository
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class ProductRepositoryImpl: ProductRepository {
    private val db = Firebase.firestore.collection("Produtos")

    override suspend fun getProducts(): Flow<List<Product>> = callbackFlow {
        val listenerRegistration = db.addSnapshotListener { querySnapshot, error ->
            if (error != null) {
                close(error)
                return@addSnapshotListener
            }
            val products = querySnapshot?.documents?.map { productItem ->
                val productDto = ProductDto(
                    id = productItem.id,
                    name = productItem.getString("name") ?: "",
                    description = productItem.getString("description") ?: "",
                    price = productItem.getDouble("price") ?: 0.00,
                    imageUrl = productItem.getString("imageUrl") ?: "",
                )
                productDto.toProduct()
            } ?: emptyList()
            trySend(products).isSuccess
        }

        awaitClose { listenerRegistration.remove() }
    }
}