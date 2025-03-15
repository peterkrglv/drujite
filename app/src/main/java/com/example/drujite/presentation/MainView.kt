package com.example.drujite.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.compose.AppTheme
import com.example.drujite.presentation.icons.AboutIcon
import com.example.drujite.presentation.icons.HomeIcon
import com.example.drujite.presentation.icons.OthersIcon
import com.example.drujite.presentation.icons.TimetableIcon

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
    data object ProfileItem : NavigationItem(screen = Screen.Profile, icon = HomeIcon)
    data object TimeTableItem : NavigationItem(screen = Screen.Timetable, icon = TimetableIcon)
    data object HomeItem : NavigationItem(screen = Screen.Home, icon = HomeIcon)
    data object AboutSessionItem : NavigationItem(screen = Screen.AboutSession, icon = AboutIcon)
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
        NavigationItem.AboutSessionItem,
        NavigationItem.OtherCharactersItem
    )

    if (navItems.any { it.screen.route == currentRoute }) {

        NavigationBar(
            containerColor = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(32.dp))
                .border(BorderStroke(1.dp, Color.Black), RoundedCornerShape(32.dp))
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
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
                    colors = NavigationBarItemColors(
                        selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        selectedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        selectedIndicatorColor = MaterialTheme.colorScheme.primary,
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