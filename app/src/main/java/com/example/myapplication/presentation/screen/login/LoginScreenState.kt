package com.example.myapplication.presentation.screen.login

import com.example.myapplication.util.Result

sealed class LoginScreenEvent {
    data class EmailUpdated(val newEmail: String): LoginScreenEvent()
    data class PasswordUpdated(val newPassword: String): LoginScreenEvent()
    data class RememberMeChanged(val rememberMe: Boolean): LoginScreenEvent()
    data object LoginBtnClicked: LoginScreenEvent()
}

data class LoginScreenState(
    val email: String = "",
    val password: String = "",
    val rememberMe: Boolean = false,
    val loginResult: Result<Unit>? = null
)