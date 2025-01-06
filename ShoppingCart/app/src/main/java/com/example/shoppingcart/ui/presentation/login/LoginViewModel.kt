package com.example.shoppingcart.ui.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingcart.data.repository.LoginRepositoryImpl
import com.example.shoppingcart.domain.use_case.LoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {
    private val repository = LoginRepositoryImpl()
    private val loginUseCase = LoginUseCase(repository)

    private val _loginState = MutableStateFlow(false)
    val loginState: StateFlow<Boolean> get() = _loginState

    fun login(email: String, password: String){
        viewModelScope.launch{
            try{
                if(email.isEmpty() || password.isEmpty())
                {
                    _loginState.value = false
                }
                else {
                    loginUseCase.invoke(email = email, password = password).collect { isLoggedIn ->
                        _loginState.value = isLoggedIn
                    }
                }
            }
            catch(e: Exception){
                throw e
            }
        }
    }

    fun getLoggedUserId(): String{
        var loggedUserId: String = ""

        viewModelScope.launch{
            try{
                loggedUserId = loginUseCase.getLoggedUserId()
            }
            catch(e: Exception){
                throw e
            }
        }
        return loggedUserId
    }
}