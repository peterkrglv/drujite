package com.example.drujite.ui.login

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
import com.example.drujite.ui.MyButton
import com.example.drujite.ui.MyTextField
import com.example.drujite.ui.MyTitle
import com.example.drujite.ui.MyTitle2
import com.example.drujite.ui.TextButtonNavigation


@Composable
fun Login() {
    MainState()
}


@Composable
fun MainState(
    //state: LoginState.Main
) {
    val phone: MutableState<String> = remember { mutableStateOf("") } //state.phone
    val password: MutableState<String> = remember { mutableStateOf("") }

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
                value = phone.value,
                label = "Телефон",
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
            MyButton(text = "Дальше", onClick = {})
        }
        TextButtonNavigation(
            text = "Нет аккаунта?",
            buttonText = "Зарегистрироваться",
            onClick = {}
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun MainPreview() {
    AppTheme {
        MainState(
            //state = LoginState.Main()
        )
    }
}