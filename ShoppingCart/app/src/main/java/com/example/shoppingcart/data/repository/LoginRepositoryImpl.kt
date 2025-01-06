package com.example.shoppingcart.data.repository

import com.example.shoppingcart.domain.repository.LoginRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

private val auth: FirebaseAuth = FirebaseAuth.getInstance()

class LoginRepositoryImpl: LoginRepository {
    override suspend fun login(email: String, password: String) : Flow<Boolean> = callbackFlow {
        try {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        trySend(true)
                    } else {
                        trySend(false)
                    }
                    close()
                }
        } catch (e: Exception) {
            close(e)
        }

        awaitClose{

        }
    }

    override suspend fun getLoggedUserId(): String{
        return auth.uid ?: ""
    }
}