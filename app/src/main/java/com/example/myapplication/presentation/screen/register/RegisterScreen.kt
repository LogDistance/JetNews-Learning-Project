package com.example.myapplication.presentation.screen.register

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myapplication.R
import com.example.myapplication.presentation.navigation.Screen
import com.example.myapplication.presentation.screen.register.RegisterScreenViewModel
import com.example.myapplication.ui.component.StyledButton
import com.example.myapplication.util.Result

@Composable
fun RegisterScreen(
    onNavigateTo: (Screen) -> Unit = {}
){
    RegisterView(
        onNavigateTo = onNavigateTo)
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
            .background(Color(0xFF0D0221))
            .padding(top = 100.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.app_name),
            fontSize = 25.sp,
            color = Color.White

        )

        OutlinedTextField(
            value = state.username,
            onValueChange = { viewModel.onEvent(RegisterScreenEvent.UsernameChanged(it)) },
            leadingIcon = {
                Icon(
                    painter = rememberVectorPainter(image = Icons.Outlined.Person),
                    contentDescription = null
                )
            },
            placeholder = { Text(text = stringResource(R.string.enter_username)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 8.dp)
                .padding(top = 200.dp)
        )

        OutlinedTextField(
            value = state.email,
            onValueChange = { viewModel.onEvent(RegisterScreenEvent.EmailChanged(it)) },
            leadingIcon = {
                Icon(
                    painter = rememberVectorPainter(image = Icons.Outlined.Email),
                    contentDescription = null
                )
            },
            placeholder = { Text(text = stringResource(R.string.enter_email)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 8.dp)
        )

        OutlinedTextField(
            value = state.password,
            onValueChange = { viewModel.onEvent(RegisterScreenEvent.PasswordChanged(it)) },
            leadingIcon = {
                Icon(
                    painter = rememberVectorPainter(image = Icons.Outlined.Lock),
                    contentDescription = null
                )
            },
            placeholder = { Text(text = stringResource(R.string.enter_password)) },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 8.dp)
        )

        StyledButton(
            onClick = { viewModel.onEvent(RegisterScreenEvent.RegisterClicked) },
            modifier = Modifier.padding(top = 30.dp)
        ) {
            Text(
                text = "Зарегистрироваться",
                fontSize = 25.sp
            )
        }

        Text(
            text = "Уже есть аккаунт?Войти",
            fontSize = 16.sp,
            color = Color.White,
            modifier = Modifier
                .padding(top = 20.dp)
                .clickable {
                    onNavigateTo(Screen.Login)
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterView(onNavigateTo = {})
}
