package com.example.news.presentation.news_list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.news.domain.model.Result

@Composable
fun NewsListScreen(
    viewModel: NewsViewModel,
    onNewsSelected: (String) -> Unit
) {
    val newsList by viewModel.news.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchNews()
    }

    LazyColumn {
        items(newsList) { newsItem ->
            NewsItem(newsItem = newsItem) {
                onNewsSelected(it)
            }
        }
    }
}

@Composable
fun NewsItem(newsItem: Result, onClick: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(newsItem.uri) }
            .padding(8.dp)
            .background(Color(0xFFE3F2FD)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = newsItem.multimedia.firstOrNull()?.url,
            contentDescription = newsItem.title,
            modifier = Modifier
                .size(200.dp)
                .padding(8.dp)
        )

        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = newsItem.title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic
            )
            Text(
                text = newsItem.section,
                fontSize = 15.sp,

            )
        }
    }
}