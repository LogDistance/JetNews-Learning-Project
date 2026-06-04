package com.example.myapplication.presentation.screen.main.feed

import com.example.myapplication.domain.model.NewsItem

sealed interface FeedScreenEvent {
     data class SearchQueryChanged(val newSearchQuery: String): FeedScreenEvent
     data class  NewsItemClicked(val newsItem: NewsItem): FeedScreenEvent
     data class  NewsItemFavoriteToggleClicked(val newsItem: NewsItem): FeedScreenEvent
}