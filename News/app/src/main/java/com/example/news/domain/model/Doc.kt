package com.example.news.domain.model

data class Doc(
    val lead_paragraph: String,
    val pub_date: String,
    val snippet: String,
    val uri: String,
)