package com.example.drujite.ui.greeting

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.compose.AppTheme
import com.example.drujite.R
import com.example.drujite.ui.GreetingText1
import com.example.drujite.ui.GreetingText2
import com.example.drujite.ui.MyButtonSmall
import com.example.drujite.ui.Screen
import org.koin.androidx.compose.koinViewModel


@Composable
fun GreetingView(
    navController: NavController,
    viewModel: GreetingViewModel = koinViewModel(),
) {
    val viewState = viewModel.viewState.collectAsState()
    val viewAction = viewModel.viewAction.collectAsState()

    when (val action = viewAction.value) {
        is GreetingAction.NavigateToLogin -> {
            navController.navigate(Screen.Login.route)
            viewModel.resetAction()
        }
        else -> {}
    }

    MainState(
        onProceedButtonClicked = {
            viewModel.obtainEvent(GreetingEvent.proceedButtonClicked)
        }
    )
}


@Composable
fun MainState(
    onProceedButtonClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        horizontalAlignment = Alignment.Start,

        ) {
        Image(
            painter = painterResource(id = R.drawable.greeting),
            contentDescription = "GreetingImage",
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp, 40.dp),
        verticalArrangement = Arrangement.Bottom
    )
    {
        GreetingText1(text = "Привет!")
        GreetingText2(text = "Добро пожаловать в помощник игрока любой смены Дружите.ру!")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            MyButtonSmall(
                text = "Поехали ->",
                onClick = onProceedButtonClicked
            )
        }

    }
}


@Preview(showSystemUi = true)
@Composable
fun MainPreview() {
    AppTheme {
        MainState(
            onProceedButtonClicked = {}
        )
    }
}