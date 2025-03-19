package com.example.drujite.presentation.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.data.SharedPrefsRepositoryImpl
import com.example.domain.repos.SharedPrefsRepository
import com.example.domain.use_cases.LogOutUseCase
import com.example.drujite.presentation.MyTextButton
import com.example.drujite.presentation.MyTitle
import com.example.drujite.presentation.Screen
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
fun ProfileView(
    navController: NavController
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        MyTitle("Здесь будет профиль")
        MyTextButton("Выйти из аккаунта") {
            navController.navigate(Screen.Login.route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    }
}