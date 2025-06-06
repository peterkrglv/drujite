package com.example.drujite.presentation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.drujite.presentation.about_session.AboutSessionView
import com.example.drujite.presentation.character_customisation.CustomisationView
import com.example.drujite.presentation.character_transfer.TransferView
import com.example.drujite.presentation.greeting.GreetingView
import com.example.drujite.presentation.home.HomeView
import com.example.drujite.presentation.login.LoginView
import com.example.drujite.presentation.other_characters.OtherCharactersView
import com.example.drujite.presentation.profile.ProfileView
import com.example.drujite.presentation.character_creation.CreationView
import com.example.drujite.presentation.session_news.NewsView
import com.example.drujite.presentation.session_selection.SessionView
import com.example.drujite.presentation.signup.SignupView
import com.example.drujite.presentation.timetable.TimeTableView


sealed class Screen(val route: String) {
    data object Greeting : Screen("greeting")
    data object Login : Screen("login")
    data object SignUp : Screen("signup")
    data object SessionSelection : Screen("session_selection")
    data object CharacterCreation : Screen("character_creation")
    data object CharacterTransfer : Screen("character_transfer")
    data object CharacterCustomisation : Screen("character_customisation")
    data object Home : Screen("home")
    data object Profile : Screen("profile")
    data object Timetable : Screen("timetable")
    data object AboutSession : Screen("about_session")
    data object OtherCharacters : Screen("other_characters")
    data object News : Screen("news")
}

@Composable
fun SetUpNavHost(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Screen.Greeting.route) {
        composable(route = Screen.Greeting.route) { GreetingView(navController) }
        composable(route = Screen.Login.route) { LoginView(navController) }
        composable(route = Screen.SignUp.route) { SignupView(navController) }
        composable(route = Screen.SessionSelection.route + "/{userToken}") { backStackEntry ->
            val userToken = backStackEntry.arguments?.getString("userToken")
            if (userToken != null) {
                SessionView(navController, userToken)
            } else {
                Log.e("navigation", "Error while navigating to session selection")
            }
        }
        composable(route = Screen.CharacterCreation.route + "/{sessionId}/{userToken}") { backStackEntry ->
            val sessionId = backStackEntry.arguments?.getString("sessionId")?.toIntOrNull()
            val userToken = backStackEntry.arguments?.getString("userToken")
            if (sessionId != null && userToken != null) {
                CreationView(navController, sessionId, userToken)
            } else {
                Log.e("navigation", "Error while navigating to character creation")
            }
        }
        composable(route = Screen.CharacterTransfer.route + "/{sessionId}/{userToken}") { backStackEntry ->
            val sessionId = backStackEntry.arguments?.getString("sessionId")?.toIntOrNull()
            val userToken = backStackEntry.arguments?.getString("userToken")
            if (sessionId != null && userToken != null) {
                TransferView(navController, sessionId, userToken)
            } else {
                Log.e("navigation", "Error while navigating to character transfer")
            }
        }
        composable(route = Screen.CharacterCustomisation.route + "/{userToken}/{sessionId}/{characterId}") { backStackEntry ->
            val userToken = backStackEntry.arguments?.getString("userToken")
            val sessionId = backStackEntry.arguments?.getString("sessionId")?.toIntOrNull()
            val characterId = backStackEntry.arguments?.getString("characterId")?.toIntOrNull()
            if (userToken != null && sessionId != null && characterId != null) {
                CustomisationView(navController, userToken, sessionId, characterId)
            } else {
                Log.e("navigation", "Error while navigating to Customisation view")
            }
        }
        composable(route = Screen.Home.route) { HomeView(navController)}
        composable(route = Screen.Profile.route) { ProfileView(navController) }
        composable(route = Screen.Timetable.route) { TimeTableView() }
        composable(route = Screen.AboutSession.route) { AboutSessionView() }
        composable(route = Screen.OtherCharacters.route) { OtherCharactersView() }
        composable(route = Screen.News.route) { NewsView() }
    }
}