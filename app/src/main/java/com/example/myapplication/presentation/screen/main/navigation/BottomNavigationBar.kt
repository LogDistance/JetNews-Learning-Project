package com.example.myapplication.presentation.screen.main.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.myapplication.R
import com.example.myapplication.util.BottomNavItem

// Cyberpunk Colors
val CyberDark = Color(0xFF0D0221)
val NeonCyan = Color(0xFF00FFFF)
val NeonPink = Color(0xFFFF00FF)

@Composable
fun BottomNavigationBar(
    navController: NavController
) {
    val bottomNavItems = listOf(
        BottomNavItem(
            icon = Icons.Outlined.Home,
            titleResId = R.string.feed,
            route = MainScreenNavigationRoute.Feed
        ),
        BottomNavItem(
            icon = Icons.Outlined.Person,
            titleResId = R.string.profile,
            route = MainScreenNavigationRoute.Profile
        )
    )
    var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }
    
    NavigationBar(
        containerColor = CyberDark,
        contentColor = NeonCyan
    ) {
        bottomNavItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItemIndex == index,
                onClick = {
                    selectedItemIndex = index
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = "BottomNavIcon"
                    )
                },
                label = {
                    Text(text = stringResource(id = item.titleResId))
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = NeonPink,
                    selectedTextColor = NeonPink,
                    unselectedIconColor = NeonCyan,
                    unselectedTextColor = NeonCyan,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}
