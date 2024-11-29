package com.example.news.data.remote.model

import com.example.news.domain.model.Result

data class ResultDto(
    val multimedia: List<MultimediaDto>,
    val published_date: String,
    val section: String,
    val title: String,
    val uri: String,
){
    fun toNewsList(): Result{
        return Result(multimedia = multimedia, published_date = published_date, section = section, title = title, uri = uri)
    }
}