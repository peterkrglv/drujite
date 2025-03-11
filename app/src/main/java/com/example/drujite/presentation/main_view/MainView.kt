package com.example.drujite.presentation.main_view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.compose.AppTheme
import com.example.drujite.presentation.MyButton
import com.example.drujite.presentation.Screen

@Composable
fun MainView(
    navController: NavController,
    userId: Int,
    sessionId: Int,
    characterId: Int
    ) {
    MainState(
        onButtonClick = {
            navController.navigate(Screen.Login.route) {
                popUpTo("${Screen.MainView.route}/${userId}/${sessionId}/${characterId}") { inclusive = true }
            }
        }
    )
}

@Composable
fun MainState(
    onButtonClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(64.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Тут будет главная страничка с персонажем и с навигацией внизу"
        )
        MyButton(
            text = "Выйти из аккаунта",
            onClick = onButtonClick
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun MainViewPreview() {
    AppTheme {
        MainState({})
    }
}