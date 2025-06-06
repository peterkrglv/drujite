package com.example.drujite.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.compose.AppTheme
import com.example.drujite.presentation.icons.NewsIcon
import com.example.drujite.presentation.icons.HomeIcon
import com.example.drujite.presentation.icons.OthersIcon
import com.example.drujite.presentation.icons.ProfileIcon
import com.example.drujite.presentation.icons.TimeTableIcon

@Composable
fun MainView(
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
                .padding(padding)
        ) {
            SetUpNavHost(navController)
        }
    }
}

sealed class NavigationItem(val screen: Screen, val icon: ImageVector) {
    data object ProfileItem : NavigationItem(screen = Screen.Profile, icon = ProfileIcon)
    data object TimeTableItem : NavigationItem(screen = Screen.Timetable, icon = TimeTableIcon)
    data object HomeItem : NavigationItem(screen = Screen.Home, icon = HomeIcon)
    data object NewsItem : NavigationItem(screen = Screen.News, icon = NewsIcon)
    data object OtherCharactersItem :
        NavigationItem(screen = Screen.OtherCharacters, icon = OthersIcon)
}


@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val navItems = listOf(
        NavigationItem.ProfileItem,
        NavigationItem.TimeTableItem,
        NavigationItem.HomeItem,
        NavigationItem.NewsItem,
        NavigationItem.OtherCharactersItem
    )

    if (navItems.any { it.screen.route == currentRoute }) {

        NavigationBar(
            containerColor = MaterialTheme.colorScheme.surface,
            modifier = Modifier
                .fillMaxWidth()
                .border(BorderStroke(1.dp, Color.Gray))
        ) {
            navItems.forEach { item ->
                NavigationBarItem(
                    selected = currentRoute == item.screen.route,
                    icon = {
                        Icon(
                            modifier = Modifier.size(32.dp),
                            imageVector = item.icon,
                            contentDescription = item.screen.route
                        )
                    },
                    onClick = {
                        if (currentRoute != item.screen.route) {
                            navController.navigate(item.screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    inclusive = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
                    colors = NavigationBarItemColors(
                        selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        selectedTextColor = MaterialTheme.colorScheme.onBackground,
                        selectedIndicatorColor = MaterialTheme.colorScheme.surface,
                        unselectedIconColor = MaterialTheme.colorScheme.onBackground,
                        unselectedTextColor = MaterialTheme.colorScheme.onBackground,
                        disabledIconColor = MaterialTheme.colorScheme.onBackground,
                        disabledTextColor = MaterialTheme.colorScheme.onBackground
                    )
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun MainScreenPreview() {
    AppTheme { MainView() }
}