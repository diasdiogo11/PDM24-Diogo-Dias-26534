package com.example.shoppingcart.data.remote.model

import com.example.shoppingcart.domain.model.CartItem

data class CartItemDto(
    val product: ProductDto,
    val quantity: Int
){
    fun toCartItem(): CartItem {
        return CartItem(
            product = product.toProduct(),
            quantity = quantity
        )
    }
}