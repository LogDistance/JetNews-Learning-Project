package com.example.myapplication.data.repository

import com.example.myapplication.domain.model.NewsItem
import java.time.LocalDateTime

import javax.inject.Inject

class NewsRepository @Inject constructor() {
    suspend fun loadNews(): List<NewsItem> {
        return  listOf(
            NewsItem(
                id = "1",
                title = "NewsItem 1",
                description = "NewsItem 1 description",
                publishedBy = "NewsSource",
                publishedAt = LocalDateTime.now(),
                imageUrl = "https://yandex.ru/images/search?from=tabbar&img_url=https%3A%2F%2Fplay-lh.googleusercontent.com%2FKBQvAVAKMNDOidk0yel5pRxAjWf6Xhm1sUSexgwRHYUSYXifracYqnNkLcAh2wi1Ug&lr=10262&p=2&pos=13&rpt=simage&text=kotlin%20image",
                isFavorite = false
            ),
            NewsItem(
                id = "2",
                title = "NewsItem 2",
                description = "NewsItem 2 description",
                publishedBy = "NewsSource",
                publishedAt = LocalDateTime.now(),
                imageUrl = "https://yandex.ru/images/search?from=tabbar&img_url=https%3A%2F%2Fvigilantlinks.com%2Fwp-content%2Fuploads%2F2022%2F10%2Fandroidstudiologo1.jpg&lr=10262&pos=5&rpt=simage&text=android%20studio",
                isFavorite = false
            )
        )
    }
}