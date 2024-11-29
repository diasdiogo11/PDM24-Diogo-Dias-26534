package com.example.news

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.news.presentation.news_list.NewsDetailsScreen
import com.example.news.presentation.news_list.NewsListScreen
import com.example.news.presentation.news_list.NewsDetailsViewModel
import com.example.news.presentation.news_list.NewsViewModel
import com.example.news.ui.theme.NewsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
                MainScreen()
        }
    }
}

@Composable
fun MainScreen(){
    var selectedNewsUri by remember { mutableStateOf<String?>(null) }

    if (selectedNewsUri == null) {
        val newsListViewModel: NewsViewModel = viewModel()
        NewsListScreen(newsListViewModel) { newsUri ->
            selectedNewsUri = newsUri
        }
    } else {
        val newsDetailViewModel: NewsDetailsViewModel = viewModel()
        NewsDetailsScreen(newsDetailViewModel, selectedNewsUri!!) {
            selectedNewsUri = null
        }
    }
}




@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NewsTheme {
    }
}