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
    override suspend fun register(email: String, password: String) : Flow<Boolean>  = callbackFlow {
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

        awaitClose{

        }
    }

    override suspend fun createUser(id: String, name: String, email: String){
        try{
            val db = Firebase.firestore.collection("Utilizadores").document(id)
            val user = hashMapOf(
                "name" to name,
                "email" to email
            )
            db.set(user).await()
        }
        catch (e: Exception) {
            throw e
        }
    }
}