package com.example.myapplication.data.repository

import com.example.myapplication.data.dto.NewsApiResponseDto
import com.example.myapplication.data.util.generateNewsItemIdFromUrl
import com.example.myapplication.data.util.toModel
import com.example.myapplication.domain.dao.FavoriteNewsDao
import com.example.myapplication.domain.model.NewsItem
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import java.time.LocalDateTime

import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val httpClient: HttpClient,
    private val favoriteNewsDao: FavoriteNewsDao
){
    suspend fun loadNews(): List<NewsItem> {
        return try {
            val response = httpClient.get("top-headlines") {
                parameter("category", "technology")
            }.body<NewsApiResponseDto>()
            val favoriteNewsIdsList = favoriteNewsDao.getAll().map { it.id }
            response.articles.map { it.toModel(isFavorite = generateNewsItemIdFromUrl(it.url) in favoriteNewsIdsList) }
        } catch (e: Exception) {
            android.util.Log.e("HTTP_CLIENT", "Error loading news", e)
            emptyList()
        }
    }
}