package com.example.news.data.repository

import com.example.news.data.remote.api.TheNewsApi
import com.example.news.domain.model.Doc
import com.example.news.domain.model.Result
import com.example.news.domain.repository.NewsRepository

class NewsRepositoryImpl(private val api: TheNewsApi) : NewsRepository {
    override suspend fun getNews(): List<Result> {
        return api.getNews().results.map { it.toNews() }
    }

    override suspend fun getNewsDetails(newsUri: String): Doc {
        val fq = "uri:\"$newsUri\""
        return api.getNewsDetails(fq).response.docs[0].toNewsDetails()
    }
}