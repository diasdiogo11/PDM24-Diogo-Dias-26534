package com.example.shoppingcart.ui.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingcart.data.repository.LoginRepositoryImpl
import com.example.shoppingcart.data.repository.RegisterRepositoryImpl
import com.example.shoppingcart.domain.use_case.LoginUseCase
import com.example.shoppingcart.domain.use_case.RegisterUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegisterViewModel: ViewModel() {
    private val registerRepository = RegisterRepositoryImpl()
    private val registerUseCase = RegisterUseCase(registerRepository)
    private val loginRepository = LoginRepositoryImpl()
    private val loginUseCase = LoginUseCase(loginRepository)

    private val _registerState = MutableStateFlow(false)
    val registerState: StateFlow<Boolean> get() = _registerState

    fun register(name: String, email: String, password: String){
        viewModelScope.launch{
            try{
                if(email.isEmpty() || password.isEmpty())
                {
                    _registerState.value = false
                }
                else {
                    registerUseCase.invoke(email = email, password = password).collect { isRegistered ->
                        _registerState.value = isRegistered
                    }
                }
            }
            catch(e: Exception){
                throw e
            }
        }
    }

    fun createUser(id: String, email: String, name: String){
        viewModelScope.launch{
            try{
                if(email.isNotEmpty() && id.isNotEmpty() && name.isNotEmpty())
                {
                    registerUseCase.createUser(id = id, email = email, name = name)
                }
            }
            catch(e: Exception){
                throw e
            }
        }
    }
}