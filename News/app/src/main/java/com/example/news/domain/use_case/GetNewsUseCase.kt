package com.example.news.domain.use_case

import com.example.news.domain.model.News
import com.example.news.domain.model.Result
import com.example.news.domain.repository.NewsRepository

class GetNewsUseCase(private val repository: NewsRepository) {
    suspend operator fun invoke():List<Result>
    {
        return repository.getNews()
    }
}