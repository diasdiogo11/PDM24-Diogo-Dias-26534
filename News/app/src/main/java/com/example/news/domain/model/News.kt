package com.example.news.domain.model

data class News(
    val results: List<Result>,
    val section: String
)