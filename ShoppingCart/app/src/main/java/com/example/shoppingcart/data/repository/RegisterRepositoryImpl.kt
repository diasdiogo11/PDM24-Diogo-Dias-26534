package com.example.shoppingcart.data.repository

import com.example.shoppingcart.domain.repository.RegisterRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class RegisterRepositoryImpl: RegisterRepository {
    override suspend fun register(email: String, password: String): Flow<Boolean> = callbackFlow {
        try {
            val auth: FirebaseAuth = FirebaseAuth.getInstance()

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        trySend(true)
                    } else {
                        trySend(false)
                    }
                }
        } catch (e: Exception) {
            close(e)
        }

        awaitClose { }
    }

    override suspend fun createUser(id: String, name: String, email: String, isVisible: Boolean) {
        try {
            val db = Firebase.firestore.collection("Utilizadores").document(id)

            // Dados do usuário, agora com o parâmetro isVisible
            val user = hashMapOf(
                "name" to name,
                "email" to email,
                "isVisible" to isVisible // Adicionando o parâmetro isVisible
            )
            db.set(user).await()

            // Criação da coleção Cart, com um documento vazio ou dados iniciais
            val cartData = hashMapOf(
                "products" to emptyList<String>(), // Lista vazia de produtos inicialmente
                "totalPrice" to 0.0 // Preço total do carrinho inicial
            )
            db.collection("Cart").document("cartData").set(cartData).await() // Criar a coleção "Cart"
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun updateVisibility(id: String, isVisible: Boolean) {
        try {
            val db = Firebase.firestore.collection("Utilizadores").document(id)

            // Atualizando apenas o campo "isVisible" no Firestore
            db.update("isVisible", isVisible).await()
        } catch (e: Exception) {
            throw e
        }
    }


}
