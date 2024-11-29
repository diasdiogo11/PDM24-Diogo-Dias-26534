package com.example.news.data.remote.api

import com.example.news.data.remote.model.NewsDetailsDto
import com.example.news.data.remote.model.NewsDto
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object RetrofitInstance{
    val api: TheNewsApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/svc/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TheNewsApi::class.java)

    }
}

interface TheNewsApi {
    @GET("topstories/v2/home.json?api-key=jpJDaGeiHBpC2OUpGYtc5FMtNW0mhxkm")
    suspend fun getNewsList(): NewsDto

    @GET("search/v2/articlesearch.json?&api-key=jpJDaGeiHBpC2OUpGYtc5FMtNW0mhxkm")
    suspend fun getNewsDetails(@Query("fq") fq: String): NewsDetailsDto
}