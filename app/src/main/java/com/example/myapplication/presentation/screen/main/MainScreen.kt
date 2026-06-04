package com.example.myapplication.presentation.screen.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.presentation.navigation.Screen
import com.example.myapplication.presentation.screen.main.feed.FeedScreen
import com.example.myapplication.presentation.screen.main.navigation.BottomNavigationBar
import com.example.myapplication.presentation.screen.main.navigation.MainScreenNavigationRoute
import com.example.myapplication.presentation.screen.main.profile.ProfileScreen

@Composable
fun MainScreen(
    onNavigateTo: (Screen) -> Unit
) {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFF0D0221), // CyberDark
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { innerPadding ->
        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            startDestination = MainScreenNavigationRoute.Feed
        ) {
            composable<MainScreenNavigationRoute.Feed> {
                FeedScreen(navigate = {})
            }
            composable<MainScreenNavigationRoute.Profile> {
                ProfileScreen()
            }
        }
    }
}
