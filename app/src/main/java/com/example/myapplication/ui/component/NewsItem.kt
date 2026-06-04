package com.example.myapplication.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.myapplication.R
import com.example.myapplication.domain.model.NewsItem
import com.example.myapplication.presentation.theme.MyApplicationTheme
import java.time.LocalDateTime

// Cyberpunk Colors (Matching FeedScreen)
val CyberDarkCard = Color(0xFF1B065E)
val NeonCyan = Color(0xFF00FFFF)
val NeonPink = Color(0xFFFF00FF)

@Composable
fun NewsItem(
    modifier: Modifier = Modifier,
    newsItem: NewsItem,
    onFavoriteClicked: () -> Unit,
    onReadClicked: () -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = CyberDarkCard,
            contentColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        ) {
            AsyncImage(
                model = newsItem.imageUrl,
                contentDescription = "Фото новости",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
            
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = newsItem.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = NeonCyan,
                    modifier = Modifier.weight(1f)
                )
                IconButton(onClick = onFavoriteClicked) {
                    Icon(
                        imageVector = if (newsItem.isFavorite) Icons.Filled.Favorite else Icons.Outlined.Favorite,
                        contentDescription = "Избранное",
                        tint = NeonPink
                    )
                }
            }

            Text(
                text = newsItem.description,
                maxLines = 3,
                fontSize = 14.sp,
                color = Color.White.copy(alpha = 0.8f),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            StyledButton(
                onClick = onReadClicked,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = stringResource(R.string.read),
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                    Icon(
                        imageVector = Icons.Outlined.PlayArrow,
                        contentDescription = "Стрелка",
                        tint = Color.Black
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun NewsItemPreview() {
    MyApplicationTheme {
        NewsItem(
            newsItem = NewsItem(
                id = "1",
                title = "Cyberpunk 2077 Update",
                description = "New patch brings neon-lit rain and flying cars to Night City.",
                publishedBy = "Night City News",
                publishedAt = LocalDateTime.now(),
                imageUrl = "https://images.unsplash.com/photo-1605810230434-7631ac76ec81",
                isFavorite = true
            ),
            onFavoriteClicked = {},
            onReadClicked = {}
        )
    }
}
