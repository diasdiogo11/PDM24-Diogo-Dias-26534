package com.example.news.data.remote.model

import com.example.news.domain.model.Multimedia
import com.example.news.domain.model.News
import com.example.news.domain.model.Result

data class NewsDto(
    val results: List<ResultDto>,
    val section: String
){

}