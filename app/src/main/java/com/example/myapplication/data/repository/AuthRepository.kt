package com.example.myapplication.data.repository

import com.example.myapplication.domain.dao.UserDao
import com.example.myapplication.domain.entity.User
import javax.inject.Inject
import com.example.myapplication.util.Result
import java.util.UUID

class AuthRepository @Inject constructor(
    private val userDao: UserDao
) {
    suspend fun login(email: String, password: String): Result<Unit> {
        val loginUser = userDao.login(email, password)
        val result = if (loginUser == null) Result.Failure<Unit>("Невозможно войти.Неверные данные")
        else Result.Success<Unit>("Вы успешно вошли!")
        return result
    }
    suspend fun register(username:String, email: String, password: String): Result<Unit>{
        if (userDao.findUserByEmail(email) != null) {
            return Result.Failure<Unit>("Пользователь с такой почтой уже существует")
        }
        val user = User(
            id = UUID.randomUUID().toString(),
            username = username,
            email = email,
            password = password
        )
        userDao.addUser(user)
        return Result.Success<Unit>("Регистрация прошла успешно!")
    }

}
