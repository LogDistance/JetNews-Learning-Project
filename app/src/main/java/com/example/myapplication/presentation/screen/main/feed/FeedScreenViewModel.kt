package com.example.myapplication.presentation.screen.main.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.repository.FavoriteNewsRepository
import com.example.myapplication.data.repository.LocalAuthManager
import com.example.myapplication.data.repository.NewsRepository
import com.example.myapplication.domain.model.NewsItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FeedScreenViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    private val favoriteNewsRepository: FavoriteNewsRepository,
    private val localAuthManager: LocalAuthManager
): ViewModel() {

    private val _state = MutableStateFlow(FeedScreenState())
    val state = _state.asStateFlow()

    private var allNews: List<NewsItem> = emptyList()

    init {
        loadNews()
    }

    fun onEvent(event: FeedScreenEvent) {
        when (event) {
            is FeedScreenEvent.NewsItemClicked -> onNewsItemClicked(event.newsItem)
            is FeedScreenEvent.SearchQueryChanged -> onSearchQueryChanged(event.newSearchQuery)
            is FeedScreenEvent.NewsItemFavoriteToggleClicked -> onNewsItemFavoriteToggleClicked(event.newsItem)
        }
    }

    private fun onNewsItemClicked(newsItem: NewsItem) {
        _state.update { currentState ->
            val isExpanded = currentState.expandedNewsIds.contains(newsItem.id)
            if (isExpanded) {
                // If already expanded, we can open the URL
                currentState.copy(selectedNewsArticleUrl = newsItem.url)
            } else {
                // Otherwise expand it
                currentState.copy(
                    expandedNewsIds = currentState.expandedNewsIds + newsItem.id
                )
            }
        }
    }

    // Add a way to reset the URL after it's been handled by the UI
    fun onUrlOpened() {
        _state.update { it.copy(selectedNewsArticleUrl = null) }
    }

    private fun onNewsItemFavoriteToggleClicked(newsItem: NewsItem) {
        allNews = allNews.map {
            if (it.id == newsItem.id) it.copy(isFavorite = !it.isFavorite)
            else it
        }
        
        updateFilteredNews()

        viewModelScope.launch(Dispatchers.IO) {
            if (newsItem.isFavorite) {
                favoriteNewsRepository.removeNewsItemFromFavorite(newsItem.id)
            } else {
                localAuthManager.getCurrentUserId()?.let { currentUserId ->
                    favoriteNewsRepository.addNewsItemToFavorite(newsItem, currentUserId)
                }
            }
        }
    }

    private fun onSearchQueryChanged(newQuery: String) {
        _state.update { it.copy(searchQuery = newQuery) }
        updateFilteredNews()
    }

    private fun loadNews() = viewModelScope.launch {
        val news = withContext(Dispatchers.IO) { newsRepository.loadNews() }
        allNews = news
        updateFilteredNews()
    }

    private fun updateFilteredNews() {
        val query = _state.value.searchQuery
        val filtered = if (query.isEmpty()) {
            allNews
        } else {
            allNews.filter {
                it.title.contains(query, ignoreCase = true) ||
                it.description.contains(query, ignoreCase = true)
            }
        }
        _state.update { it.copy(filteredNews = filtered) }
    }
}
