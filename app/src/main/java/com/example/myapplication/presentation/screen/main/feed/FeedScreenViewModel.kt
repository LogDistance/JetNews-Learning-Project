package com.example.myapplication.presentation.screen.main.feed

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.repository.NewsRepository
import com.example.myapplication.domain.model.NewsItem
import com.example.myapplication.presentation.navigation.Screen
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = FeedScreenViewModel.Factory::class)
class FeedScreenViewModel @AssistedInject constructor(
    @Assisted val navigate: (Screen) -> Unit,
    private val newsRepository: NewsRepository
) : ViewModel() {

    private val _state = MutableStateFlow(FeedScreenState())
    val state = _state.asStateFlow()

    private var allNews: List<NewsItem> = emptyList()


    init {
        loadNews()
    }


    private fun loadNews() {
        viewModelScope.launch {
            allNews = newsRepository.loadNews()
            _state.update { it.copy(filteredNews = allNews) }
        }
    }

    fun onEvent(event: FeedScreenEvent) {
        when (event) {
            is FeedScreenEvent.NewsItemClicked -> {
                // Здесь можно использовать navigate(Screen.Detail(event.newsItem.id))
            }
            is FeedScreenEvent.SearchQueryChanged -> onSearchQueryChanged(event.newSearchQuery)
            is FeedScreenEvent.NewsItemFavoriteToggleClicked -> onNewsItemFavoriteToggleClicked(event.newsItem)
        }
    }

    private fun onNewsItemFavoriteToggleClicked(newsItem: NewsItem){
        val updatedNews = state.value.filteredNews.map {
            if (it.id == newsItem.id) newsItem.copy(isFavorite = !newsItem.isFavorite)
            else it
        }
        _state.update{it.copy(filteredNews = updatedNews)}

    }


    private fun onSearchQueryChanged(newQuery: String) {
        val filtered = if (newQuery.isEmpty()) {
            allNews
        } else {
            allNews.filter {
                it.title.contains(newQuery, ignoreCase = true) ||
                it.description.contains(newQuery, ignoreCase = true)
            }
        }
            viewModelScope.launch {
                _state.update { it.copy(
                searchQuery = newQuery,
                filteredNews = filtered
            ) }
            }
    }
        


    @AssistedFactory
    interface Factory {
        fun create(navigate: (Screen) -> Unit): FeedScreenViewModel
    }
}

