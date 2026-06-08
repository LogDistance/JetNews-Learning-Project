package com.example.myapplication.data.repository

import com.example.myapplication.data.util.toFavoriteNewsItemEntity
import com.example.myapplication.data.util.toModel
import com.example.myapplication.domain.dao.FavoriteNewsDao
import com.example.myapplication.domain.model.NewsItem
import javax.inject.Inject

class FavoriteNewsRepository @Inject constructor(
    private val favoriteNewsDao: FavoriteNewsDao
){
    suspend fun addNewsItemToFavorite(newsItem: NewsItem, currentUserId: String) {
        val favoriteNewsItemEntity = newsItem.toFavoriteNewsItemEntity(savedByUserId = currentUserId)
        favoriteNewsDao.addFavoriteNewsItem(favoriteNewsItemEntity)
    }

    suspend fun removeNewsItemFromFavorite(id: String) {
        favoriteNewsDao.deleteFavoriteNewsItemById(id)
    }

    suspend fun getAllFavoriteNews(): List<NewsItem> {
        return favoriteNewsDao.getAll().map { it.toModel() }
    }
}