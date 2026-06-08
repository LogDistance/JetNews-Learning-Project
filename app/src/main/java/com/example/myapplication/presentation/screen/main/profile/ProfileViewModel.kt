package com.example.myapplication.presentation.screen.main.profile

import androidx.lifecycle.ViewModel
import com.example.myapplication.data.repository.LocalAuthManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val localAuthManager: LocalAuthManager
) : ViewModel() {

    fun logout() {
        localAuthManager.signOut()
    }
}
