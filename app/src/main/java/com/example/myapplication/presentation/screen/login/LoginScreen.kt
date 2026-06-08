package com.example.myapplication.presentation.screen.login

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.myapplication.R
import com.example.myapplication.presentation.navigation.Screen
import com.example.myapplication.presentation.theme.CyanNeon
import com.example.myapplication.presentation.theme.CyberDark
import com.example.myapplication.presentation.theme.PinkNeon
import com.example.myapplication.presentation.ui.component.StyledButton
import com.example.myapplication.util.Result

@Composable
fun LoginScreen(
    onNavigateTo: (Screen) -> Unit
) {
    val viewModel = hiltViewModel<LoginScreenViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    val context = LocalContext.current
    LaunchedEffect(state.loginResult) {
        state.loginResult?.let { loginResult ->
            when(loginResult) {
                is Result.Success<Unit> -> {
                    onNavigateTo(Screen.Main)
                }
                is Result.Failure<Unit> -> {
                    Toast.makeText(context, loginResult.msg, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(CyberDark)
    ) {
        LoginView(
            state = state,
            onNavigateTo = onNavigateTo,
            onEvent = viewModel::onEvent
        )
    }
}

@Composable
fun LoginView(
    onNavigateTo: (Screen) -> Unit = {},
    state: LoginScreenState = LoginScreenState(),
    onEvent: (LoginScreenEvent) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(80.dp))
        
        Text(
            text = "ACCESS REQUIRED",
            fontSize = 32.sp,
            color = CyanNeon,
            fontWeight = FontWeight.ExtraBold
        )
        
        Text(
            text = "NIGHT CITY NETWORK",
            fontSize = 14.sp,
            color = PinkNeon,
            modifier = Modifier.padding(bottom = 60.dp)
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.email,
            onValueChange = { onEvent(LoginScreenEvent.EmailUpdated(it)) },
            leadingIcon = {
                Icon(
                    painter = rememberVectorPainter(image = Icons.Outlined.Email),
                    contentDescription = null,
                    tint = CyanNeon
                )
            },
            placeholder = {
                Text(
                    text = stringResource(id = R.string.enter_email).uppercase(),
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
            shape = CutCornerShape(topStart = 8.dp, bottomEnd = 8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = state.password,
            onValueChange = { onEvent(LoginScreenEvent.PasswordUpdated(it)) },
            leadingIcon = {
                Icon(
                    painter = rememberVectorPainter(image = Icons.Outlined.Lock),
                    contentDescription = null,
                    tint = CyanNeon
                )
            },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            placeholder = {
                Text(
                    text = stringResource(id = R.string.enter_password).uppercase(),
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
            shape = CutCornerShape(topStart = 8.dp, bottomEnd = 8.dp)
        )

        Spacer(modifier = Modifier.height(40.dp))

        StyledButton(
            onClick = { onEvent(LoginScreenEvent.LoginBtnClicked) },
            modifier = Modifier.fillMaxWidth(),
            containerColor = CyanNeon,
            contentColor = Color.Black
        ) {
            Text(
                text = stringResource(id = R.string.login).uppercase(),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
        
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = stringResource(id = R.string.no_account_register).uppercase(),
            fontSize = 12.sp,
            color = CyanNeon,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .clickable { onNavigateTo(Screen.Register) }
                .padding(8.dp)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun LoginScreenPreview() {
    Box(modifier = Modifier.fillMaxSize().background(CyberDark)) {
        LoginView()
    }
}
