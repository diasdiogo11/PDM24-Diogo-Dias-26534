package com.example.news.presentation.news_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.data.remote.api.RetrofitInstance
import com.example.news.data.repository.NewsRepositoryImpl
import com.example.news.domain.model.News
import com.example.news.domain.model.Result
import com.example.news.domain.use_case.GetNewsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {
    private val api = RetrofitInstance.api
    private val repository = NewsRepositoryImpl(api)
    private val getNewsUseCase = GetNewsUseCase(repository)

    val news = MutableStateFlow<List<Result>>(emptyList())


    fun fetchNews() {
        viewModelScope.launch {
            try {
                news.value = getNewsUseCase()
            } catch (e: Exception) {
                news.value = emptyList()
            }
        }
    }
}


