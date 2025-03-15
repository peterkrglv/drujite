package com.example.drujite.presentation

import CreationView
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.drujite.presentation.about_session.AboutSessionView
import com.example.drujite.presentation.character_customisation.CustomisationView
import com.example.drujite.presentation.character_transfer.TransferView
import com.example.drujite.presentation.greeting.GreetingView
import com.example.drujite.presentation.home.HomeView
import com.example.drujite.presentation.login.LoginView
import com.example.drujite.presentation.other_characters.OtherCharactersView
import com.example.drujite.presentation.profile.ProfileView
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
}

@Composable
fun SetUpNavHost(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Screen.Greeting.route) {
        composable(route = Screen.Greeting.route) { GreetingView(navController) }
        composable(route = Screen.Login.route) { LoginView(navController) }
        composable(route = Screen.SignUp.route) { SignupView(navController) }
        composable(route = Screen.SessionSelection.route + "/{userId}") { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId")?.toIntOrNull()
            if (userId != null) {
                SessionView(navController, userId)
            } else {
                Log.e("navigation", "Error while navigating to session selection")
            }
        }
        composable(route = Screen.CharacterCreation.route + "/{sessionId}/{userId}") { backStackEntry ->
            val sessionId = backStackEntry.arguments?.getString("sessionId")?.toIntOrNull()
            val userId = backStackEntry.arguments?.getString("userId")?.toIntOrNull()
            if (sessionId != null && userId != null) {
                CreationView(navController, sessionId, userId)
            } else {
                Log.e("navigation", "Error while navigating to character creation")
            }
        }
        composable(route = Screen.CharacterTransfer.route + "/{sessionId}/{userId}") { backStackEntry ->
            val sessionId = backStackEntry.arguments?.getString("sessionId")?.toIntOrNull()
            val userId = backStackEntry.arguments?.getString("userId")?.toIntOrNull()
            if (sessionId != null && userId != null) {
                TransferView(navController, sessionId, userId)
            } else {
                Log.e("navigation", "Error while navigating to character transfer")
            }
        }
        composable(route = Screen.CharacterCustomisation.route + "/{userId}/{sessionId}/{characterId}") { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId")?.toIntOrNull()
            val sessionId = backStackEntry.arguments?.getString("sessionId")?.toIntOrNull()
            val characterId = backStackEntry.arguments?.getString("characterId")?.toIntOrNull()
            if (userId != null && sessionId != null && characterId != null) {
                CustomisationView(navController, userId, sessionId, characterId)
            } else {
                Log.e("navigation", "Error while navigating to Customisation view")
            }
        }
        composable(route = Screen.Home.route) { HomeView()}
        composable(route = Screen.Profile.route) { ProfileView() }
        composable(route = Screen.Timetable.route) { TimeTableView() }
        composable(route = Screen.AboutSession.route) { AboutSessionView() }
        composable(route = Screen.OtherCharacters.route) { OtherCharactersView() }
    }
}