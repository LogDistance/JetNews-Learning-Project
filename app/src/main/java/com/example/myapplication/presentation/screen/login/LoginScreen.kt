package com.example.myapplication.presentation.screen.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

import com.example.myapplication.R
import com.example.myapplication.util.Result
import com.example.myapplication.presentation.navigation.Screen
import com.example.myapplication.ui.component.StyledButton

@Composable
fun LoginScreen(
    onNavigateTo: (Screen) -> Unit = {},
    viewModel: LoginScreenViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state = viewModel.state
    val loginResult = viewModel.state.loginResult
    LaunchedEffect(loginResult) {
        when (loginResult) {
            is Result.Success -> {
                onNavigateTo(Screen.Main)
            }

            is Result.Failure -> {
                Toast.makeText(context, loginResult.msg, Toast.LENGTH_LONG).show()
            }

            null -> {}
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0D0221))
            .padding(top = 100.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(R.string.app_name),
            fontSize = 25.sp,
            color = Color.White

        )

        Image(
            painter = painterResource(R.drawable.news_app_logo),
            contentDescription = "News app login image",
            modifier = Modifier
                .size(180.dp)
                .padding(top = 16.dp)
        )

        OutlinedTextField(
            value = state.email,
            onValueChange = { viewModel.onEvent(LoginScreenEvent.EmailUpdated(it)) },
            leadingIcon = {
                Icon(
                    painter = rememberVectorPainter(image = Icons.Outlined.Email),
                    contentDescription = null
                )

            },
            placeholder = {
                Text(text = stringResource(R.string.enter_email))
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 16.dp)
        )

        OutlinedTextField(
            value = state.password,
            onValueChange = { viewModel.onEvent(LoginScreenEvent.PasswordUpdated(it)) },
            leadingIcon = {
                Icon(
                    painter = rememberVectorPainter(image = Icons.Outlined.Lock),
                    contentDescription = null
                )
            },
            placeholder = {
                Text(text = stringResource(R.string.enter_password))
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            visualTransformation = PasswordVisualTransformation()
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = state.rememberMe,
                onCheckedChange = { viewModel.onEvent(LoginScreenEvent.RememberMeChanged(it)) }
            )
            Text(text = "Запомнить меня",
                color = Color.White)

        }

        StyledButton(
            onClick = { viewModel.onEvent(LoginScreenEvent.LoginBtnClicked) },
            modifier = Modifier.padding(top = 30.dp)
        ) {
            Text(
                text = stringResource(R.string.login),
                fontSize = 25.sp,

            )
        }

        Text(
            text = stringResource(R.string.register),
            fontSize = 16.sp,
            color = Color.White,
            modifier = Modifier
                .padding(top = 20.dp)
                .clickable {
                    onNavigateTo(Screen.Register)
                }
        )
    }
}

@Composable
@Preview(showBackground = true)
fun LoginScreenPreview() {
    LoginScreen()
}
