package com.example.myapplication.util

sealed interface Result<out T> {
    data class Success<T>(val msg: String = "", val data: T? = null) : Result<T>
    data class Failure<T>(val msg: String = "", val data: T? = null) : Result<T>
}
