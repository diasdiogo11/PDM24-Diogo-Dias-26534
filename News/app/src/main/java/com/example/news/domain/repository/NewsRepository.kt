package com.example.news.domain.repository

import com.example.news.domain.model.News

interface NewsRepository {
    suspend fun getNews(): List<News>
}