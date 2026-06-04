package com.example.myapplication.domain.model

import android.icu.text.CaseMap
import java.time.LocalDateTime

data class NewsItem (
    val id : String,
    val title: String,
    val description: String,
    val publishedBy: String,
    val publishedAt: LocalDateTime,
    val imageUrl: String,
    val isFavorite: Boolean
)
