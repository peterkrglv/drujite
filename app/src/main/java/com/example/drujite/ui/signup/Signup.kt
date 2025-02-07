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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.AppTheme
import com.example.drujite.ui.GenderChoice
import com.example.drujite.ui.MyButton
import com.example.drujite.ui.MyTextField
import com.example.drujite.ui.MyTitle
import com.example.drujite.ui.MyTitle2
import com.example.drujite.ui.TextButtonNavigation


@Composable
fun Signup() {
    MainState()
}


@Composable
fun MainState(
    //state: SignupState.Main
) {
    val name: MutableState<String> = remember { mutableStateOf("") } //state.name
    val phone: MutableState<String> = remember { mutableStateOf("") } //state.phone
    val password: MutableState<String> = remember { mutableStateOf("") }
    val repeatedPassword: MutableState<String> = remember { mutableStateOf("") }
    val gender: MutableState<String> = remember { mutableStateOf("M") }

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
                value = name.value,
                label = "Имя и фамилия",
                isError = false,
                onValueChange = {
                    name.value = it
                })
            MyTextField(
                value = phone.value,
                label = "Твой телефон",
                isError = false,
                onValueChange = {
                    phone.value = it
                })
            MyTextField(
                value = password.value,
                label = "Пароль",
                isError = false,
                onValueChange = {
                    password.value = it
                })
            MyTextField(
                value = repeatedPassword.value,
                label = "Подтвердите пароль",
                isError = false
            )
            {
                repeatedPassword.value = it
            }
            GenderChoice(
                onFClick = {
                    gender.value = "F"
                },
                onMClick = {
                    gender.value = "M"
                },
                gender = gender.value
            )
            MyButton(text = "Дальше", onClick = {})
        }
        TextButtonNavigation(
            text = "Уже есть аккаунт?",
            buttonText = "Войти",
            onClick = {}
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun MainPreview() {
    AppTheme {
        MainState(
            //state = SignupState.Main()
        )
    }
}