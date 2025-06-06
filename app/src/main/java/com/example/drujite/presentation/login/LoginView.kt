package com.example.drujite.presentation.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.compose.AppTheme
import com.example.domain.use_cases.user.LoginResult
import com.example.drujite.R
import com.example.drujite.presentation.Screen
import com.example.drujite.presentation.my_composables.LoadingScreen
import com.example.drujite.presentation.my_composables.MyButton
import com.example.drujite.presentation.my_composables.MyPasswordField
import com.example.drujite.presentation.my_composables.MyTextField
import com.example.drujite.presentation.my_composables.MyTitle
import com.example.drujite.presentation.my_composables.MyTitle2
import com.example.drujite.presentation.my_composables.TextButtonNavigation
import org.koin.androidx.compose.koinViewModel


@Composable
fun LoginView(
    navController: NavController,
    viewModel: LoginViewModel = koinViewModel()
) {
    val viewState = viewModel.viewState.collectAsState()
    val viewAction = viewModel.viewAction.collectAsState()

    when (val action = viewAction.value) {
        is LoginAction.NavigateToSignup -> {
            navController.navigate(Screen.SignUp.route)
            viewModel.clearAction()
        }

        is LoginAction.NavigateToSessionSelection -> {
            navController.navigate("${Screen.SessionSelection.route}/${action.userToken}") {
                popUpTo(Screen.Login.route) { inclusive = true }
            }
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

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 160.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        )
        {
            MyTitle(text = stringResource(R.string.login_title))
            MyTitle2(text = stringResource(R.string.login_subtitile))
            Spacer(modifier = Modifier.height(32.dp))
            MyTextField(
                value = phone,
                label = stringResource(R.string.phone),
                errorText = if (error == LoginResult.WRONG_PHONE || error == LoginResult.INVALID_PHONE) error.message else null,
                onValueChange = {
                    onPhoneChanged(it)
                })
            MyPasswordField(
                value = password,
                label = stringResource(R.string.password),
                errorText = if (error == LoginResult.WRONG_PASSWORD) error.message else null,
                onValueChange = {
                    onPasswordChanged(it)
                })
            MyButton(text = stringResource(R.string.next), onClick = onProceedClicked)
        }
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            TextButtonNavigation(
                text = stringResource(R.string.login_to_signup),
                buttonText = stringResource(R.string.signp),
                onClick = onSignUpClicked
            )
        }
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