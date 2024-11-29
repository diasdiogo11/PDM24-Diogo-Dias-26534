package com.example.news.data.remote.model

import com.example.news.domain.model.Doc


data class DocDto(
    val lead_paragraph: String,
    val pub_date: String,
    val snippet: String,
    val uri: String,

){
    fun toNewsDetails(): Doc {
        return Doc(lead_paragraph = lead_paragraph, pub_date = pub_date, snippet = snippet, uri = uri)
    }
}