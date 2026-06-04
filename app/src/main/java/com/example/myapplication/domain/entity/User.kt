package com.example.myapplication.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey
    val id: String,
    @ColumnInfo("email")
    val email: String,
    @ColumnInfo("username")
    val username: String,
    @ColumnInfo("password")
    val password: String
)
