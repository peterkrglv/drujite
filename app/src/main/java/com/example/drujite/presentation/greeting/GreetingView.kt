package com.example.drujite.presentation.greeting

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.compose.AppTheme
import com.example.drujite.R
import com.example.drujite.presentation.Screen
import com.example.drujite.presentation.my_composables.GreetingText1
import com.example.drujite.presentation.my_composables.GreetingText2
import com.example.drujite.presentation.my_composables.LoadingScreen
import org.koin.androidx.compose.koinViewModel


@Composable
fun GreetingView(
    navController: NavController,
    viewModel: GreetingViewModel = koinViewModel(),
) {
    val viewState = viewModel.viewState.collectAsState()
    val viewAction = viewModel.viewAction.collectAsState()

    when (viewAction.value) {
        is GreetingAction.NavigateToLogin -> {
            viewModel.clearAction()
            navController.navigate(Screen.Login.route) {
                popUpTo(Screen.Greeting.route) { inclusive = true }
            }
            Log.d("GreetingView", "NavigateToLogin")
        }

        is GreetingAction.NavigateToMainView -> {
            viewModel.clearAction()
            navController.navigate(Screen.Home.route) {
                popUpTo(Screen.Greeting.route) { inclusive = true }
            }
            Log.d("GreetingView", "NavigateToMainView")
        }

        else -> {}
    }

    when (viewState.value) {
        is GreetingState.Main -> {
            MainState(
                onProceedButtonClicked = {
                    viewModel.obtainEvent(GreetingEvent.ProceedButtonClicked)
                }
            )
        }

        is GreetingState.Loading -> {
            LoadingScreen()
        }

        GreetingState.Initialization -> viewModel.obtainEvent(GreetingEvent.CheckIfUserIsLoggedIn)
    }
}


@Composable
fun MainState(
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
            GreetingText1(text = stringResource(R.string.greeting_title))
            GreetingText2(text = stringResource(R.string.greeting_subtitle))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                ElevatedButton(
                    modifier = Modifier
                        .padding(16.dp)
                        .size(160.dp, 45.dp),
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
