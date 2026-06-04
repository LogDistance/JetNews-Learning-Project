package com.example.myapplication.util

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.myapplication.presentation.screen.main.navigation.MainScreenNavigationRoute

data class BottomNavItem(
    val icon: ImageVector,
    val titleResId: Int,
    var route: MainScreenNavigationRoute
) {}
