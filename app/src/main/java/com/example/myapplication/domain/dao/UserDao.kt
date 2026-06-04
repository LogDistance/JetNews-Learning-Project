package com.example.myapplication.domain.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.domain.entity.User

@Dao
interface UserDao {

    @Insert
    suspend fun addUser(user: User)

    @Delete
    suspend fun removeUser(user: User)


    @Query("SELECT * FROM User WHERE email = :email")
    suspend fun findUserByEmail(email: String): User?

    @Query("SELECT * FROM User WHERE email = :email AND password = :password")
    suspend fun login (email :String, password: String): User?

}
