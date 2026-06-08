package com.example.myapplication.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.data.repository.LocalAuthManager
import com.example.myapplication.presentation.navigation.MainNav


import com.example.myapplication.presentation.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var localAuthManager: LocalAuthManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainContent(
                        modifier = Modifier.padding(innerPadding),
                        isLoggedIn = localAuthManager.isLoggedIn()
                    )
                }
            }
        }
    }
}

@Composable
fun MainContent(
    modifier: Modifier = Modifier,
    isLoggedIn: Boolean
) {
    MainNav(
        navHostController = rememberNavController(),
        modifier = modifier,
        isLoggedIn = isLoggedIn
    )
}

@Preview(showBackground = true)@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        MainContent(isLoggedIn = false)
    }
}
