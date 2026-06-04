package com.example.myapplication.presentation.screen.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.presentation.MainViewModel
import com.example.myapplication.presentation.navigation.MainNavigation
import com.example.myapplication.presentation.navigation.Screen
import com.example.myapplication.presentation.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                val viewModel: MainViewModel = hiltViewModel()
                val startDestination by viewModel.startDestination.collectAsState()

                if (startDestination != null) {
                    MainContent(startDestination = startDestination!!)
                }
            }
        }
    }
}

@Composable
fun MainContent(
    startDestination: Screen
) {
    val navHostController = rememberNavController()
    MainNavigation(
        navHostController = navHostController,
        startDestination = startDestination
    )
}
