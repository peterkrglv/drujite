package com.example.drujite.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.AppTheme
import com.example.drujite.ui.MyButton
import com.example.drujite.ui.MyText
import com.example.drujite.ui.MyTextButton
import com.example.drujite.ui.MyTextField
import com.example.drujite.ui.MyTitle
import com.example.drujite.ui.MyTitle2


@Composable
fun Login() {
    MainState()
}


@Composable
fun MainState(
    //state: LoginState.Main
) {
    //val phone: MutableState<String> = remember { mutableStateOf(state.phone) }
    //val password: MutableState<String> = remember { mutableStateOf(state.password) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
            MyTitle(text = "Создадим аккаунт?")
            MyTitle2(text = "Lorem Ipsum - это текст-\"рыба\", часто используемый в печати и вэб-дизайне.")
            Spacer(modifier = Modifier.height(32.dp))
            MyTextField(
                value = "Твое имя и фамилия",
                label = "Имя и Фамилия",
                isError = false,
                onValueChange = {})
            MyTextField(
                value = "Телефон",
                label = "Твой телефон",
                isError = false,
                onValueChange = {})
            MyTextField(
                value = "Пароль",
                label = "Придумай пароль",
                isError = false,
                onValueChange = {})
            MyTextField(
                value = "Повтори пароль",
                label = "Повтори пароль",
                isError = false,
                onValueChange = {})
            MyButton(text = "Дальше", onClick = {})
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize()
                .padding(64.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            MyText(text = "Уже есть аккаунт?")
            MyTextButton(text = "Войти", onClick = {})
        }
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