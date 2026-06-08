package com.example.myapplication.presentation.screen.register

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.myapplication.R
import com.example.myapplication.presentation.navigation.Screen
import com.example.myapplication.presentation.theme.CyanNeon
import com.example.myapplication.presentation.theme.CyberDark
import com.example.myapplication.presentation.theme.PinkNeon
import com.example.myapplication.presentation.ui.component.StyledButton
import com.example.myapplication.util.Result

@Composable
fun RegisterScreen(
    onNavigateTo: (Screen) -> Unit = {}
){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(CyberDark)
    ) {
        RegisterView(onNavigateTo = onNavigateTo)
    }
}

@Composable
fun RegisterView(
    onNavigateTo: (Screen) -> Unit,
    viewModel: RegisterScreenViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state = viewModel.state
    val registrationResult = state.registrationResult

    LaunchedEffect(registrationResult) {
        when (registrationResult) {
            is Result.Success -> {
                Toast.makeText(context, registrationResult.msg, Toast.LENGTH_SHORT).show()
                onNavigateTo(Screen.Main)
            }
            is Result.Failure -> {
                Toast.makeText(context, registrationResult.msg, Toast.LENGTH_LONG).show()
            }
            null -> {}
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(80.dp))

        Text(
            text = "NEW IDENTITY",
            fontSize = 32.sp,
            color = CyanNeon,
            fontWeight = FontWeight.ExtraBold
        )

        Text(
            text = "CREATE ACCOUNT",
            fontSize = 14.sp,
            color = PinkNeon,
            modifier = Modifier.padding(bottom = 60.dp)
        )

        OutlinedTextField(
            value = state.username,
            onValueChange = { viewModel.onEvent(RegisterScreenEvent.UsernameChanged(it)) },
            leadingIcon = {
                Icon(
                    painter = rememberVectorPainter(image = Icons.Outlined.Person),
                    contentDescription = null,
                    tint = CyanNeon
                )
            },
            placeholder = {
                Text(
                    text = stringResource(R.string.enter_username).uppercase(),
                    color = CyanNeon.copy(alpha = 0.5f)
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedBorderColor = PinkNeon,
                unfocusedBorderColor = CyanNeon,
                cursorColor = PinkNeon
            ),
            shape = CutCornerShape(topStart = 8.dp, bottomEnd = 8.dp),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = state.email,
            onValueChange = { viewModel.onEvent(RegisterScreenEvent.EmailChanged(it)) },
            leadingIcon = {
                Icon(
                    painter = rememberVectorPainter(image = Icons.Outlined.Email),
                    contentDescription = null,
                    tint = CyanNeon
                )
            },
            placeholder = {
                Text(
                    text = stringResource(R.string.enter_email).uppercase(),
                    color = CyanNeon.copy(alpha = 0.5f)
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedBorderColor = PinkNeon,
                unfocusedBorderColor = CyanNeon,
                cursorColor = PinkNeon
            ),
            shape = CutCornerShape(topStart = 8.dp, bottomEnd = 8.dp),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = state.password,
            onValueChange = { viewModel.onEvent(RegisterScreenEvent.PasswordChanged(it)) },
            leadingIcon = {
                Icon(
                    painter = rememberVectorPainter(image = Icons.Outlined.Lock),
                    contentDescription = null,
                    tint = CyanNeon
                )
            },
            placeholder = {
                Text(
                    text = stringResource(R.string.enter_password).uppercase(),
                    color = CyanNeon.copy(alpha = 0.5f)
                )
            },
            visualTransformation = PasswordVisualTransformation(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedBorderColor = PinkNeon,
                unfocusedBorderColor = CyanNeon,
                cursorColor = PinkNeon
            ),
            shape = CutCornerShape(topStart = 8.dp, bottomEnd = 8.dp),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(40.dp))

        StyledButton(
            onClick = { viewModel.onEvent(RegisterScreenEvent.RegisterClicked) },
            modifier = Modifier.fillMaxWidth(),
            containerColor = CyanNeon,
            contentColor = Color.Black
        ) {
            Text(
                text = stringResource(R.string.register).uppercase(),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = stringResource(R.string.already_have_an_account).uppercase(),
            fontSize = 12.sp,
            color = CyanNeon,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .clickable { onNavigateTo(Screen.Login) }
                .padding(8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    Box(modifier = Modifier.fillMaxSize().background(CyberDark)) {
        RegisterView(onNavigateTo = {})
    }
}
