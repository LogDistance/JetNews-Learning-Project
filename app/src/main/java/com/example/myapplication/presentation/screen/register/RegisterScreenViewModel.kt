package com.example.myapplication.presentation.screen.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.repository.AuthRepository
import com.example.myapplication.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterScreenViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    var state by mutableStateOf(RegisterScreenState())
        private set

    fun onEvent(event: RegisterScreenEvent) {
        when (event) {
            is RegisterScreenEvent.UsernameChanged -> {
                state = state.copy(username = event.newUsername)
            }
            is RegisterScreenEvent.EmailChanged -> {
                state = state.copy(email = event.newEmail)
            }
            is RegisterScreenEvent.PasswordChanged -> {
                state = state.copy(password = event.newPassword)
            }
            RegisterScreenEvent.RegisterClicked -> {
                register()
            }
        }
    }

    private fun register() {
        if (state.username.isEmpty() || state.email.isEmpty() || state.password.isEmpty()) {
            state = state.copy(registrationResult = Result.Failure("Все поля должны быть заполнены"))
            return
        }

        viewModelScope.launch {
            val result = authRepository.register(
                state.username,
                state.email,
                state.password
            )
            state = state.copy(registrationResult = result)
        }
    }
}