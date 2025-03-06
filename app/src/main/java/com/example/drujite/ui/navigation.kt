package com.example.drujite.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.drujite.ui.greeting.GreetingView
import com.example.drujite.ui.login.LoginView
import com.example.drujite.ui.session_selection.SessionView
import com.example.drujite.ui.signup.SignupView


sealed class Screen(val route: String) {
    data object Greeting : Screen("greeting")
    data object Login: Screen("login")
    data object SignUp: Screen("signup")
    data object SessionSelection: Screen("session_selection")
    data object MainView: Screen("main_view")
}

@Composable
fun SetUpNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Greeting.route) {
        composable(route = Screen.Greeting.route) { GreetingView(navController) }
        composable(route = Screen.Login.route) { LoginView(navController) }
        composable(route = Screen.SignUp.route) { SignupView(navController) }
        composable(route = Screen.SessionSelection.route) { SessionView(navController) }
    }
}