package com.example.news.data.repository

import com.example.news.data.remote.api.TheNewsApi
import com.example.news.domain.model.News
import com.example.news.domain.repository.NewsRepository

class NewsRepositoryImpl(private val api: TheNewsApi) : NewsRepository {
    override suspend fun getNews(): List<News> {
        return api.getNews().map { it.toNews() }
    }
}