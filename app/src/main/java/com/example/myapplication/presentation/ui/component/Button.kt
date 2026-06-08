package com.example.myapplication.presentation.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myapplication.presentation.theme.CyanNeon
import com.example.myapplication.presentation.theme.CyberDark

@Composable
fun StyledButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    containerColor: Color = CyanNeon,
    contentColor: Color = Color.Black,
    content: @Composable () -> Unit
) {
    // Cyberpunk Style Button with Cut Corners
    Button(
        modifier = modifier,
        onClick = onClick,
        shape = CutCornerShape(topStart = 8.dp, bottomEnd = 8.dp), 
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        border = BorderStroke(1.dp, contentColor.copy(alpha = 0.5f))
    ) {
        content()
    }
}
