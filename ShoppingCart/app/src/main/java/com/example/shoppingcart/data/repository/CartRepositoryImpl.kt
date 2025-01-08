package com.example.shoppingcart.data.repository

import com.example.shoppingcart.data.remote.model.ProductDto
import com.example.shoppingcart.domain.model.Cart
import com.example.shoppingcart.domain.model.CartItem
import com.example.shoppingcart.domain.model.Product
import com.example.shoppingcart.domain.repository.CartRepository
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class CartRepositoryImpl : CartRepository {
    private val db = Firebase.firestore.collection("Utilizadores")

    override suspend fun getCart(userId: String): Flow<Cart> = callbackFlow {
        val listenerRegistration = db.document(userId)
            .collection("Cart")
            .addSnapshotListener { querySnapshot, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }

                val cartItems = querySnapshot?.documents?.map { cartItem ->
                    val productDto = ProductDto(
                        id = cartItem.getString("productId") ?: "",
                        name = cartItem.getString("name") ?: "",
                        description = cartItem.getString("description") ?: "",
                        price = cartItem.getDouble("price") ?: 0.0,
                        imageUrl = cartItem.getString("imageUrl")
                    )
                    val quantity = cartItem.getLong("quantity")?.toInt() ?: 0

                    CartItem(
                        product = productDto.toProduct(),
                        quantity = quantity
                    )
                } ?: emptyList()

                val cart = Cart(
                    userId = userId,
                    products = cartItems,
                    totalItems = cartItems.sumOf { it.quantity },
                    totalPrice = cartItems.sumOf { it.quantity * it.product.price }
                )

                trySend(cart).isSuccess
            }

        awaitClose { listenerRegistration.remove() }
    }

    override suspend fun addToCart(userId: String, product: Product, quantity: Int) {
        val cartCollection = db.document(userId).collection("Cart")

        // Check if the product already exists in the cart
        val productDoc = cartCollection.document(product.id)
        val snapshot = productDoc.get().await()

        if (snapshot.exists()) {
            // Update quantity of existing product
            val existingQuantity = snapshot.getLong("quantity")?.toInt() ?: 0
            val newQuantity = existingQuantity + quantity
            productDoc.update("quantity", newQuantity).await()
        } else {
            // Add new product to the cart
            val cartItemData = hashMapOf(
                "productId" to product.id,
                "name" to product.name,
                "description" to product.description,
                "price" to product.price,
                "imageUrl" to product.imageUrl,
                "quantity" to quantity
            )
            productDoc.set(cartItemData).await()
        }
    }
}
