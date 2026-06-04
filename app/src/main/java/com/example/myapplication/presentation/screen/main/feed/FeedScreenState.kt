package com.example.myapplication.presentation.screen.main.feed

import com.example.myapplication.domain.model.NewsItem

data class FeedScreenState(
    val searchQuery: String ="",
    val filteredNews: List<NewsItem> = emptyList()
) {
}