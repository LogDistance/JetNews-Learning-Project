package com.example.myapplication.ui.component

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun StyledButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    // Cyberpunk Style Button
    Button(
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(size = 8.dp), // Sharper corners for cyberpunk
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF00FFFF), // Neon Cyan
            contentColor = Color.Black
        )
    ) {
        content()
    }
}
