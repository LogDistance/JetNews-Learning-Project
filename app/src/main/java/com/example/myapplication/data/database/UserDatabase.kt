package com.example.myapplication.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.domain.dao.UserDao
import com.example.myapplication.domain.entity.User

import com.example.myapplication.domain.entity.FavoriteNewsItemEntity
import com.example.myapplication.domain.dao.FavoriteNewsDao

@Database(entities = [User::class, FavoriteNewsItemEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
 abstract fun getUserDao(): UserDao
 abstract fun getFavoriteNewsDao(): FavoriteNewsDao
}