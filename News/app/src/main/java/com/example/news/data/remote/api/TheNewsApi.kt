package com.example.news.data.remote.api

import com.example.news.data.remote.model.NewsDto
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

object Retrofitinstance{
    val api: TheNewsApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/svc/topstories/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TheNewsApi::class.java)

    }
}

interface TheNewsApi {
    @GET("v2/home")
    suspend fun getNews(): List<NewsDto>

}