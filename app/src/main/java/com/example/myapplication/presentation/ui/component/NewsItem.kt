package com.example.myapplication.presentation.ui.component

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowForward
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.KeyboardArrowDown
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
import com.example.myapplication.presentation.theme.CyanNeon
import com.example.myapplication.presentation.theme.CyberDark
import com.example.myapplication.presentation.theme.CyberGray
import com.example.myapplication.presentation.theme.MyApplicationTheme
import com.example.myapplication.presentation.theme.PinkNeon
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@Composable
fun NewsItem(
    modifier: Modifier = Modifier,
    newsItem: NewsItem,
    isExpanded: Boolean = false,
    onFavoriteClicked: () -> Unit,
    onReadClicked: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .animateContentSize(), // Smoothly animate expansion
        colors = CardDefaults.cardColors(
            containerColor = CyberGray
        ),
        shape = CutCornerShape(topStart = 16.dp, bottomEnd = 16.dp),
        border = BorderStroke(2.dp, if (isExpanded) PinkNeon else CyanNeon)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box {
                AsyncImage(
                    model = newsItem.imageUrl,
                    contentDescription = "Фото новости",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clip(CutCornerShape(topStart = 16.dp)),
                    contentScale = ContentScale.Crop
                )
                // Source tag
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .background(PinkNeon)
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = newsItem.publishedBy.uppercase(),
                        color = Color.Black,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = newsItem.title,
                    color = CyanNeon,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.ExtraBold,
                    maxLines = if (isExpanded) Int.MAX_VALUE else 2
                )
                
                Text(
                    text = newsItem.description,
                    color = Color.White.copy(alpha = 0.8f),
                    maxLines = if (isExpanded) Int.MAX_VALUE else 3,
                    fontSize = 14.sp,
                    lineHeight = 18.sp
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    StyledButton(
                        modifier = Modifier.weight(1f),
                        onClick = onReadClicked,
                        containerColor = if (isExpanded) PinkNeon else CyanNeon,
                        contentColor = Color.Black
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = (if (isExpanded) "OPEN LINK" else stringResource(R.string.read)).uppercase(),
                                fontWeight = FontWeight.Bold
                            )
                            Icon(
                                imageVector = if (isExpanded) Icons.AutoMirrored.Outlined.ArrowForward else Icons.Outlined.KeyboardArrowDown,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }

                    // Heart button (Favorite)
                    IconButton(
                        onClick = onFavoriteClicked,
                        modifier = Modifier
                            .background(
                                color = if (newsItem.isFavorite) PinkNeon else Color.Transparent,
                                shape = CutCornerShape(4.dp)
                            )
                            .border(
                                width = 1.dp,
                                color = PinkNeon,
                                shape = CutCornerShape(4.dp)
                            )
                            .size(40.dp)
                    ) {
                        Icon(
                            imageVector = if (newsItem.isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                            contentDescription = "Избранное",
                            tint = if (newsItem.isFavorite) Color.Black else PinkNeon,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalTime::class)
@Preview
@Composable
fun NewsItemPreview() {
    MyApplicationTheme {
        Box(modifier = Modifier.background(CyberDark).padding(16.dp)) {
            NewsItem(
                newsItem = NewsItem(
                    id = "1",
                    title = "NEURAL LINK ESTABLISHED: THE FUTURE IS NOW",
                    url = null,
                    description = "Scientists have successfully connected a human brain to the global data stream, marking the beginning of a new era for humanity.",
                    publishedBy = "Night City News",
                    publishedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                    imageUrl = "",
                    isFavorite = true
                ),
                onFavoriteClicked = {},
                onReadClicked = {}
            )
        }
    }
}
