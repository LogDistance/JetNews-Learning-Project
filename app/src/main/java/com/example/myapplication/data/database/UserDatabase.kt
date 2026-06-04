package com.example.myapplication.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.domain.dao.UserDao
import com.example.myapplication.domain.entity.User


@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
 abstract  fun getUserDao(): UserDao
}