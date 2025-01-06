package com.example.shoppingcart.data.remote.model

import com.example.shoppingcart.domain.model.Product

data class ProductDto(
    val id: String,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String?
){
    fun toProduct(): Product {
        return Product(id = id, name = name, price = price, description = description, imageUrl = imageUrl)
    }
}