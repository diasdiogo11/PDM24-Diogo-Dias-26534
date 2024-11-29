package com.example.news.domain.model

import com.example.news.data.remote.model.MultimediaDto

data class Result(
    val multimedia: List<MultimediaDto>,
    val published_date: String,
    val section: String,
    val title: String,
    val uri: String,
)