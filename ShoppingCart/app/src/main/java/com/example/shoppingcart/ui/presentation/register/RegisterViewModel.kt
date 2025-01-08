package com.example.shoppingcart.ui.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingcart.data.repository.RegisterRepositoryImpl
import com.example.shoppingcart.domain.use_case.RegisterUseCase
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegisterViewModel: ViewModel() {
    private val registerRepository = RegisterRepositoryImpl()
    private val registerUseCase = RegisterUseCase(registerRepository)

    private val _registerState = MutableStateFlow(false)
    val registerState: StateFlow<Boolean> get() = _registerState

    fun register(name: String, email: String, password: String) {
        viewModelScope.launch {
            try {
                if (email.isEmpty() || password.isEmpty()) {
                    _registerState.value = false
                } else {
                    registerUseCase.invoke(email = email, password = password).collect { isRegistered ->
                        if (isRegistered) {
                            val auth = FirebaseAuth.getInstance()
                            val userId = auth.currentUser?.uid
                            if (userId != null) {
                                registerUseCase.createUser(id = userId, email = email, name = name)
                            }
                            _registerState.value = true
                        } else {
                            _registerState.value = false
                        }
                    }
                }
            } catch (e: Exception) {
                _registerState.value = false
                throw e
            }
        }
    }
}