package com.example.shoppingcart.data.remote.model

import com.example.shoppingcart.domain.model.User

data class UserDto(
    val id: String,
    val name: String,
    val email: String,
    val isVisible: Boolean = true
) {
    fun toUser(isVisible: Boolean = true): User {
        return User(id = id, name = name, email = email, isVisible = isVisible)
    }
}