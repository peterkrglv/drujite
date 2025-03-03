package com.example.drujite.ui.login

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.compose.AppTheme
import com.example.domain.LoginResult
import com.example.drujite.ui.LoadingScreen
import com.example.drujite.ui.MyButton
import com.example.drujite.ui.MyTextField
import com.example.drujite.ui.MyTitle
import com.example.drujite.ui.MyTitle2
import com.example.drujite.ui.Screen
import com.example.drujite.ui.TextButtonNavigation
import org.koin.androidx.compose.koinViewModel


@Composable
fun LoginView(
    navController: NavController,
    viewModel: LoginViewModel = koinViewModel()
) {
    val viewState = viewModel.viewState.collectAsState()
    val viewAction = viewModel.viewAction.collectAsState()

    Log.d("aaaa", "state: $viewState, action: $viewAction")
    when (val action = viewAction.value) {
        is LoginAction.NavigateToSignup -> {
            navController.navigate(Screen.SignUp.route)
            viewModel.clearAction()
        }

        is LoginAction.NavigateToSessionSelection -> {
            navController.navigate(Screen.SessionSelection.route)
            Log.d("aaa", "NavigateToSessionSelection")
            viewModel.clearAction()
        }

        else -> {}
    }

    when (val state = viewState.value) {
        is LoginState.Main -> {
            MainState(
                state = state,
                onPhoneChanged = { viewModel.obtainEvent(LoginEvent.PhoneChanged(it)) },
                onPasswordChanged = { viewModel.obtainEvent(LoginEvent.PasswordChanged(it)) },
                onProceedClicked = { viewModel.obtainEvent(LoginEvent.ProceedClicked) },
                onSignUpClicked = { viewModel.obtainEvent(LoginEvent.SignupClicked) }
            )
        }

        is LoginState.Loading -> {
            LoadingScreen()
        }
    }

}


@Composable
fun MainState(
    state: LoginState.Main,
    onPhoneChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onProceedClicked: () -> Unit,
    onSignUpClicked: () -> Unit
) {
    val phone = state.phone
    val password = state.password
    val error = state.error

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 192.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        )
        {
            MyTitle(text = "Вход")
            MyTitle2(text = "Если ты уже не первый раз у нас, то у тебя наверняка есть аккаунт")
            Spacer(modifier = Modifier.height(32.dp))
            MyTextField(
                value = phone,
                label = "Телефон",
                errorText = if (error == LoginResult.WRONG_PHONE || error == LoginResult.INVALID_PHONE) error.message else null,
                onValueChange = {
                    onPhoneChanged(it)
                })
            MyTextField(
                value = password,
                label = "Пароль",
                errorText = if (error == LoginResult.WRONG_PASSWORD) error.message else null,
                onValueChange = {
                    onPasswordChanged(it)
                })
            MyButton(text = "Дальше", onClick = onProceedClicked)
        }
        TextButtonNavigation(
            text = "Нет аккаунта?",
            buttonText = "Зарегистрироваться",
            onClick = onSignUpClicked
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun MainPreview() {
    AppTheme {
        MainState(
            state = LoginState.Main(
                phone = "",
                password = "",
                error = null,
            ),
            onPhoneChanged = {},
            onPasswordChanged = {},
            onProceedClicked = {},
            onSignUpClicked = {}
        )
    }
}