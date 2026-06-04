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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.domain.model.NewsItem
import com.example.myapplication.presentation.theme.MyApplicationTheme
import com.example.myapplication.ui.component.NewsItem as NewsItemCard
import java.time.LocalDateTime

// Cyberpunk Colors
val CyberDark = Color(0xFF0D0221)
val NeonCyan = Color(0xFF00FFFF)
val NeonPink = Color(0xFFFF00FF)

@Composable
fun FeedScreen() {
    var searchText by remember { mutableStateOf("") }

    val sampleNewsItems = remember {
        listOf(
            NewsItem(
                id = "1",
                title = "Cyberpunk 2077 Update",
                description = "New patch brings neon-lit rain and flying cars to Night City.",
                publishedBy = "Night City News",
                publishedAt = LocalDateTime.now(),
                imageUrl = "https://images.unsplash.com/photo-1605810230434-7631ac76ec81",
                isFavorite = true
            ),
            NewsItem(
                id = "2",
                title = "Android Studio 'Cyber' Edition",
                description = "Google announces a new theme with neon highlights and fast-build tech.",
                publishedBy = "Tech Daily",
                publishedAt = LocalDateTime.now().minusHours(2),
                imageUrl = "https://images.unsplash.com/photo-1550745165-9bc0b252726f",
                isFavorite = false
            ),
            NewsItem(
                id = "3",
                title = "Kotlin Multiplatform Future",
                description = "JetBrains shows off new capabilities for cross-platform cyberpunk apps.",
                publishedBy = "Kotlin Weekly",
                publishedAt = LocalDateTime.now().minusDays(1),
                imageUrl = "https://images.unsplash.com/photo-1517694712202-14dd9538aa97",
                isFavorite = false
            )
        )
    }

    val filteredNews = sampleNewsItems.filter {
        it.title.contains(searchText, ignoreCase = true) ||
                it.description.contains(searchText, ignoreCase = true)
    }




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
            onValueChange = { onEvent(FeedScreenEvent.SearchQueryChanged(it))},
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
                    onFavoriteClicked = {},
                    onReadClicked = {
                        onEvent(FeedScreenEvent.NewsItemClicked(news))

                    }
                )
            }
        }
    }
}

}

@Preview(showBackground = true)
@Composable
fun FeedScreenPreview() {
    MyApplicationTheme {
        FeedScreen()
    }
}


