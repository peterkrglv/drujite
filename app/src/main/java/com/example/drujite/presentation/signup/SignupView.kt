package com.example.drujite.presentation.signup

import androidx.compose.foundation.layout.Arrangement
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
import com.example.domain.use_cases.user.SignupResult
import com.example.drujite.R
import com.example.drujite.presentation.Screen
import com.example.drujite.presentation.my_composables.GenderChoice
import com.example.drujite.presentation.my_composables.LoadingScreen
import com.example.drujite.presentation.my_composables.MyButton
import com.example.drujite.presentation.my_composables.MyPasswordField
import com.example.drujite.presentation.my_composables.MyTextField
import com.example.drujite.presentation.my_composables.MyTitle
import com.example.drujite.presentation.my_composables.MyTitle2
import com.example.drujite.presentation.my_composables.TextButtonNavigation
import org.koin.androidx.compose.koinViewModel


@Composable
fun SignupView(
    navController: NavController,
    viewModel: SignupViewModel = koinViewModel()
) {
    val viewState = viewModel.viewState.collectAsState()
    val viewAction = viewModel.viewAction.collectAsState()

    when (val action = viewAction.value) {
        is SignupAction.NavigateToLogin -> {
            navController.popBackStack()
            viewModel.clearAction()
        }

        is SignupAction.NavigateToSessionSelection -> {
            navController.navigate("${Screen.SessionSelection.route}/${action.userToken}") {
                popUpTo(Screen.Login.route) { inclusive = true }
            }
            viewModel.clearAction()
            viewModel.clearAction()
        }

        else -> {
        }
    }

    when (val state = viewState.value) {
        is SignupState.Main -> {
            MainState(
                state = state,
                onNameChanged = { viewModel.obtainEvent(SignupEvent.NameChanged(it)) },
                onPhoneChanged = { viewModel.obtainEvent(SignupEvent.PhoneChanged(it)) },
                onPasswordChanged = { viewModel.obtainEvent(SignupEvent.PasswordChanged(it)) },
                onRepeatedPasswordChanged = {
                    viewModel.obtainEvent(
                        SignupEvent.PasswordRepeatedChanged(
                            it
                        )
                    )
                },
                onGenderChanged = { viewModel.obtainEvent(SignupEvent.GenderChanged(it)) },
                onProceedClicked = { viewModel.obtainEvent(SignupEvent.ProceedClicked) },
                onLoginClicked = { viewModel.obtainEvent(SignupEvent.LoginClicked) }
            )
        }

        is SignupState.Loading -> {
            LoadingScreen()
        }
    }
}


@Composable
fun MainState(
    state: SignupState.Main,
    onNameChanged: (String) -> Unit,
    onPhoneChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onRepeatedPasswordChanged: (String) -> Unit,
    onGenderChanged: (Gender) -> Unit,
    onProceedClicked: () -> Unit,
    onLoginClicked: () -> Unit
) {
    val name = state.name
    val phone = state.phone
    val password = state.password
    val repeatedPassword = state.passwordRepeated
    val gender = state.gender
    val error = state.error

    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth().padding(top = 64.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            MyTitle(text = stringResource(R.string.signup_title))
            MyTitle2(text = stringResource(R.string.singup_subtitle))
            Spacer(modifier = Modifier.height(32.dp))
            MyTextField(
                value = name,
                label = stringResource(R.string.signup_name),
                errorText = null,
                onValueChange = {
                    onNameChanged(it)
                })
            MyTextField(
                value = phone,
                label = stringResource(R.string.phone),
                errorText = if (error == SignupResult.INVALID_PHONE || error == SignupResult.PHONE_ALREADY_REGISTERED) error.message else null,
                onValueChange = {
                    onPhoneChanged(it)
                })
            MyPasswordField(
                value = password,
                label = stringResource(R.string.password),
                errorText = if (error == SignupResult.INVALID_PASSWORD) error.message else null,
                onValueChange = {
                    onPasswordChanged(it)
                })
            MyPasswordField(
                value = repeatedPassword,
                label = stringResource(R.string.password_confirm),
                errorText = if (error == SignupResult.PASSWORDS_DO_NOT_MATCH) error.message else null,
                onValueChange = {
                    onRepeatedPasswordChanged(it)
                })
            GenderChoice(
                onGenderClick = onGenderChanged,
                gender = gender
            )
            MyButton(text = stringResource(R.string.proceed), onClick = onProceedClicked)
        }
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            TextButtonNavigation(
                text = stringResource(R.string.signup_to_login),
                buttonText = stringResource(R.string.signup_to_login_2),
                onClick = onLoginClicked
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun MainPreview() {
    AppTheme {
        MainState(
            state = SignupState.Main(
                name = "",
                phone = "",
                password = "",
                passwordRepeated = "",
                gender = Gender.MALE,
                error = null
            ),
            onNameChanged = {},
            onPhoneChanged = {},
            onPasswordChanged = {},
            onRepeatedPasswordChanged = {},
            onGenderChanged = {},
            onProceedClicked = {},
            onLoginClicked = {}
        )
    }
}