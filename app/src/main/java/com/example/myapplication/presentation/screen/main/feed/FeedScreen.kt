package com.example.myapplication.presentation.screen.main.feed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.myapplication.R
import com.example.myapplication.presentation.navigation.Screen
import com.example.myapplication.presentation.theme.MyApplicationTheme
import com.example.myapplication.ui.component.NewsItem as NewsItemCard

// Киберпанк Цвета
val CyberDark = Color(0xFF0D0221)
val NeonCyan = Color(0xFF00FFFF)
val NeonPink = Color(0xFFFF00FF)

@Composable
fun FeedScreen(
    navigate: (Screen) -> Unit
) {
    // 1. Поправили синтаксис фабрики Hilt ViewModel
    val viewModel = hiltViewModel<FeedScreenViewModel, FeedScreenViewModel.Factory> {
        it.create(navigate)
    }

    val state by viewModel.state.collectAsStateWithLifecycle()

    // 2. Удалили неиспользуемую локальную searchText, так как поиск идет через стейт ВьюМодели

    FeedScreenContent(
        state = state,
        onEvent = viewModel::onEvent
    )
} // <--- Добавили пропущенную закрывающую скобку

@Composable
fun FeedScreenContent(
    state: FeedScreenState,
    onEvent: (FeedScreenEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CyberDark)
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = state.searchQuery,
            onValueChange = { onEvent(FeedScreenEvent.SearchQueryChanged(it)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            shape = RoundedCornerShape(18.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(R.string.search),
                    tint = NeonCyan
                )
            },
            placeholder = {
                Text(
                    text = stringResource(R.string.search),
                    color = NeonCyan.copy(alpha = 0.5f)
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = NeonPink,
                unfocusedBorderColor = NeonCyan,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                cursorColor = NeonPink
            ),
            singleLine = true
        )

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(state.filteredNews) { news ->
                NewsItemCard(
                    modifier = Modifier.padding(vertical = 8.dp),
                    newsItem = news,
                    onFavoriteClicked = { /* Обработка клика на избранное */ },
                    onReadClicked = {
                        onEvent(FeedScreenEvent.NewsItemClicked(news))
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FeedScreenPreview() {
    MyApplicationTheme {
        // Передаем пустую лямбду в превью, чтобы не ругалось
        FeedScreen(navigate = {})
    }
}