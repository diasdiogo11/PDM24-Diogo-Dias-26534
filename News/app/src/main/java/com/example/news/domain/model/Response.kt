package com.example.news.domain.model

import com.example.news.data.remote.model.DocDto

data class Response(
    val docs: List<DocDto>,
)