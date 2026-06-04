package com.example.myapplication.presentation.screen.register

import com.example.myapplication.util.Result

sealed class RegisterScreenEvent {
    data class UsernameChanged(val newUsername: String) : RegisterScreenEvent()
    data class EmailChanged(val newEmail: String) : RegisterScreenEvent()
    data class PasswordChanged(val newPassword: String) : RegisterScreenEvent()
    data object RegisterClicked : RegisterScreenEvent()
}

data class RegisterScreenState(
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val registrationResult: Result<Unit>? = null
)
