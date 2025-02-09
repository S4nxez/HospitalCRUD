package com.example.primer.compose.crud.contactos.ui.utils

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.primer.compose.crud.contactos.ui.navigation.AppDestination
import com.example.primer.compose.crud.contactos.ui.navigation.AppMainBottomDestination


@Composable
fun BottomBar(
    navController: NavController,
    screens: List<AppDestination>,
    ) {

    NavigationBar {
        val state = navController.currentBackStackEntryAsState()

        val currentDestination = state.value?.destination
        screens.forEach { screen ->
            if (screen is AppMainBottomDestination) {
                NavigationBarItem(
                    icon = { Icon(screen.icon, contentDescription = null) } ,
                    label = { Text(screen.title) },
                    selected = currentDestination?.hierarchy?.any { it.route == screen.route::class.qualifiedName } == true,
                    onClick = {

                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }

                            launchSingleTop = true

                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}