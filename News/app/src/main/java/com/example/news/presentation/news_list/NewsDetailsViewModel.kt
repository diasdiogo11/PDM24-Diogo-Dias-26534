package com.example.news.presentation.news_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.data.remote.api.RetrofitInstance
import com.example.news.data.repository.NewsRepositoryImpl
import com.example.news.domain.model.Doc
import com.example.news.domain.use_case.GetNewsDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


class NewsDetailsViewModel : ViewModel() {

    private val api = RetrofitInstance.api
    private val repository = NewsRepositoryImpl(api)
    private val getNewsDetailsUseCase = GetNewsDetailsUseCase(repository)

    val newsDetails = MutableStateFlow<Doc?>(null)

    fun fetchNewsDetails(newsUri: String) {
        viewModelScope.launch {
            try {
                newsDetails.value = getNewsDetailsUseCase(newsUri)
            } catch (e: Exception) {
                newsDetails.value = null
            }
        }
    }
}



