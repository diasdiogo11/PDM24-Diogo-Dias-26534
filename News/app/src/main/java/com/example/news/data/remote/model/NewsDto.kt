package com.example.news.data.remote.model

import com.example.news.domain.model.Multimedia
import com.example.news.domain.model.News

data class NewsDto(
    val copyright: String,
    val last_updated: String,
    val num_results: Int,
    val results: List<ResultDto>,
    val section: String,
    val status: String
){
}