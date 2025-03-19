package com.example.drujite.presentation.greeting

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.compose.AppTheme
import com.example.drujite.R
import com.example.drujite.presentation.GreetingText1
import com.example.drujite.presentation.GreetingText2
import com.example.drujite.presentation.MyButtonSmall
import com.example.drujite.presentation.Screen
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
            viewModel.clearAction()
            navController.navigate(Screen.Login.route) {
                popUpTo(Screen.Greeting.route) { inclusive = true }
            }
        }

        is GreetingAction.NavigateToMainView -> {
            viewModel.clearAction()
            navController.navigate(Screen.Home.route) {
                popUpTo(Screen.Greeting.route) { inclusive = true }
            }
        }

        else -> {}
    }

    when (val state = viewState.value) {
        is GreetingState.Main -> {
            MainState(
                onProceedButtonClicked = {
                    viewModel.obtainEvent(GreetingEvent.ProceedButtonClicked)
                }
            )
        }

        is GreetingState.Loading -> {
            viewModel.obtainEvent(GreetingEvent.CheckIfUserIsLoggedIn)
            MainState(
                loading = true,
                onProceedButtonClicked = {}
            )
        }
    }
}


@Composable
fun MainState(
    loading: Boolean = false,
    onProceedButtonClicked: () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
                .offset(y = (-40).dp),
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
                .padding(24.dp),
            verticalArrangement = Arrangement.Bottom
        )
        {
            GreetingText1(text = "Привет!")
            GreetingText2(text = "Добро пожаловать в помощник игрока любой смены Дружите.ру!")
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                ElevatedButton(
                    modifier = Modifier
                        .padding(16.dp)
                        .size(160.dp, 45.dp)
                        .alpha(if (loading) 0f else 1f)
                        .clickable(enabled = !loading) { },
                    onClick = onProceedButtonClicked,
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = Color.Black
                    )
                ) {
                    Text(
                        fontSize = 18.sp,
                        text = "Поехали ->"
                    )
                }
            }
        }
        if (loading) CircularProgressIndicator()
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
