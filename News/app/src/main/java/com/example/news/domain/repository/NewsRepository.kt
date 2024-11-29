package com.example.news.domain.repository

import com.example.news.domain.model.Doc
import com.example.news.domain.model.Result

interface NewsRepository {
    suspend fun getNews(): List<Result>
    suspend fun getNewsDetails(newsUri: String): Doc
}