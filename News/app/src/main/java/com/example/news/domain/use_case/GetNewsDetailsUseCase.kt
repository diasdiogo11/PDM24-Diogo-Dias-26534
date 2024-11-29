package com.example.news.domain.use_case

import com.example.news.domain.model.Doc
import com.example.news.domain.repository.NewsRepository

class GetNewsDetailsUseCase(private val repository: NewsRepository) {
    suspend operator fun invoke(newsUri: String): Doc {
        return repository.getNewsDetails(newsUri)
    }
}