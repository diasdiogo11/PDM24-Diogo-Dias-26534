package com.example.news.domain.use_case

import com.example.news.domain.model.News
import com.example.news.domain.repository.NewsRepository

class GetNewsUseCase(private val repository: NewsRepository) {
    suspend operator fun invoke():List<News>
    {
        return repository.getNews()
    }
}