package com.example.drujite.ui.signup

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
import com.example.domain.use_cases.SignupResult
import com.example.drujite.ui.GenderChoice
import com.example.drujite.ui.LoadingScreen
import com.example.drujite.ui.MyButton
import com.example.drujite.ui.MyPasswordField
import com.example.drujite.ui.MyTextField
import com.example.drujite.ui.MyTitle
import com.example.drujite.ui.MyTitle2
import com.example.drujite.ui.Screen
import com.example.drujite.ui.TextButtonNavigation
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
            navController.navigate(Screen.SessionSelection.route) {
                popUpTo(Screen.SignUp.route) { inclusive = true }
            }
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(top = 128.dp),
        verticalArrangement = Arrangement.SpaceBetween

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        )
        {
            MyTitle(text = "Создадим аккаунт?")
            MyTitle2(text = "Lorem Ipsum - это текст-\"рыба\", часто используемый в печати и вэб-дизайне.")
            Spacer(modifier = Modifier.height(32.dp))
            MyTextField(
                value = name,
                label = "Имя и фамилия",
                errorText = null,
                onValueChange = {
                    onNameChanged(it)
                })
            MyTextField(
                value = phone,
                label = "Твой телефон",
                errorText = if (error == SignupResult.INVALID_PHONE || error == SignupResult.PHONE_ALREADY_REGISTERED) error.message else null,
                onValueChange = {
                    onPhoneChanged(it)
                })
            MyPasswordField(
                value = password,
                label = "Пароль",
                errorText = if (error == SignupResult.INVALID_PASSWORD) error.message else null,
                onValueChange = {
                    onPasswordChanged(it)
                })
            MyPasswordField(
                value = repeatedPassword,
                label = "Подтвердите пароль",
                errorText = if (error == SignupResult.PASSWORDS_DO_NOT_MATCH) error.message else null,
                onValueChange = {
                    onRepeatedPasswordChanged(it)
                })
            GenderChoice(
                onGenderClick = onGenderChanged,
                gender = gender
            )
            MyButton(text = "Дальше", onClick = onProceedClicked)
        }
        TextButtonNavigation(
            text = "Уже есть аккаунт?",
            buttonText = "Войти",
            onClick = onLoginClicked
        )
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