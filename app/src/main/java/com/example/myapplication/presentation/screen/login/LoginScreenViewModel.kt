package com.example.myapplication.presentation.screen.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.repository.AuthRepository
import com.example.myapplication.util.Result
import com.example.myapplication.util.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    var state by mutableStateOf(LoginScreenState())
        private set

    fun onEvent(event: LoginScreenEvent) {
        when (event) {
            is LoginScreenEvent.EmailUpdated -> {
                state = state.copy(email = event.newEmail)
            }
            is LoginScreenEvent.PasswordUpdated -> {
                state = state.copy(password = event.newPassword)
            }
            is LoginScreenEvent.RememberMeChanged -> {
                state = state.copy(rememberMe = event.rememberMe)
            }
            LoginScreenEvent.LoginBtnClicked -> {
                login()
            }
        }
    }

    private fun login() {
        if (state.email.isBlank() || state.password.isBlank()) {
            state = state.copy(loginResult = Result.Failure("Пожалуйста, заполните все поля"))
            return
        }

        viewModelScope.launch {
            val email = state.email
            val password = state.password
            val result = authRepository.login(email, password)
            state = state.copy(loginResult = result)
            
            if (result is Result.Success && state.rememberMe) {
                sessionManager.setLoggedIn(true)
            }
        }
    }
}
