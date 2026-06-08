package com.example.myapplication.presentation.screen.main.feed

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.myapplication.R
import com.example.myapplication.presentation.theme.CyanNeon
import com.example.myapplication.presentation.theme.CyberDark
import com.example.myapplication.presentation.theme.PinkNeon

@Composable
fun FeedScreen() {
    val viewModel = hiltViewModel<FeedScreenViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(state.selectedNewsArticleUrl) {
        state.selectedNewsArticleUrl?.let { url ->
            val intent = Intent(Intent.ACTION_VIEW, url.toUri())
            context.startActivity(intent)
            viewModel.onUrlOpened()
        }
    }
    
    Box(modifier = Modifier
        .fillMaxSize()
        .background(CyberDark)
    ) {
        FeedScreenContent(
            state = state,
            onEvent = viewModel::onEvent
        )
    }
}

@Composable
fun FeedScreenContent(
    state: FeedScreenState,
    onEvent: (FeedScreenEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "NIGHT CITY FEED",
            color = CyanNeon,
            fontSize = 24.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = state.searchQuery,
            onValueChange = { onEvent(FeedScreenEvent.SearchQueryChanged(it)) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search icon",
                    tint = CyanNeon
                )
            },
            placeholder = {
                Text(
                    text = stringResource(R.string.search_through_news).uppercase(),
                    fontSize = 14.sp,
                    color = CyanNeon.copy(alpha = 0.5f)
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedBorderColor = PinkNeon,
                unfocusedBorderColor = CyanNeon,
                cursorColor = PinkNeon
            ),
            shape = CutCornerShape(topStart = 8.dp, bottomEnd = 8.dp),
            modifier = Modifier
                .fillMaxWidth(0.9f)
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            modifier = Modifier.fillMaxWidth(0.9f),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            items(state.filteredNews) { newsItem ->
                val isExpanded = state.expandedNewsIds.contains(newsItem.id)
                com.example.myapplication.presentation.ui.component.NewsItem(
                    newsItem = newsItem,
                    isExpanded = isExpanded,
                    onFavoriteClicked = {
                        onEvent(FeedScreenEvent.NewsItemFavoriteToggleClicked(newsItem))
                    },
                    onReadClicked = {
                        onEvent(FeedScreenEvent.NewsItemClicked(newsItem))
                    }
                )
            }
        }
    }
}


@Preview
@Composable
fun FeedScreenPreview(){
    FeedScreen()
}



