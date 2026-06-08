package com.example.myapplication.presentation.screen.main.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.myapplication.presentation.theme.CyanNeon
import com.example.myapplication.presentation.theme.CyberDark
import com.example.myapplication.presentation.theme.PinkNeon
import com.example.myapplication.presentation.ui.component.StyledButton

@Composable
fun ProfileScreen(
    onLogout: () -> Unit,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CyberDark)
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "SYSTEM: USER_PROFILE",
            fontSize = 28.sp,
            fontWeight = FontWeight.ExtraBold,
            color = CyanNeon
        )
        
        Text(
            text = "STATUS: ONLINE",
            fontSize = 14.sp,
            color = PinkNeon,
            modifier = Modifier.padding(top = 8.dp)
        )

        Spacer(modifier = Modifier.height(60.dp))

        StyledButton(
            modifier = Modifier.fillMaxWidth(0.8f),
            onClick = {
                viewModel.logout()
                onLogout()
            },
            containerColor = PinkNeon,
            contentColor = Color.Black
        ) {
            Text(
                text = "DISCONNECT / LOGOUT",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = "CAUTION: TERMINATING SESSION...",
            fontSize = 10.sp,
            color = CyanNeon.copy(alpha = 0.5f)
        )
    }
}
