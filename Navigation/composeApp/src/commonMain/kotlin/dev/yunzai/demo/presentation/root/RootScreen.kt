package dev.yunzai.demo.presentation.root

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.yunzai.demo.domain.bottomBarDestinations
import dev.yunzai.demo.navigation.BottomBarGraph

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RootScreen(
    navigateToSettings: () -> Unit
) {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination by remember {
        derivedStateOf { backStackEntry?.destination?.route }
    }

    LaunchedEffect(currentDestination) {
        println("CURRENT ROUTE: $currentDestination")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = currentDestination
                            ?.substringAfterLast(".")
                            ?.substringBefore("/")
                            ?.substringBefore("?")
                            ?: "Unknown"
                    )
                },
                navigationIcon = {
                    AnimatedVisibility(
                        visible = currentDestination?.contains("Details") == true
                    ) {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                contentDescription = null
                            )
                        }
                    }
                },
                actions = {
                    AnimatedVisibility(
                        visible = currentDestination?.contains("Details") == false
                    ) {
                        IconButton(onClick = navigateToSettings) {
                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = null
                            )
                        }
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                bottomBarDestinations.forEach { destination ->
                    NavigationBarItem(
                        selected = checkIfSelected(
                            currentDestinationRoute = currentDestination,
                            currentBottomBarItem = destination.screen.toString()
                        ),
                        label = { Text(destination.screen.toString()) },
                        icon = {
                            Icon(
                                imageVector = destination.icon,
                                contentDescription = null
                            )
                        },
                        onClick = {
                            navController.navigate(destination.screen) {
                                popUpTo(navController.graph.findStartDestination().route!!) {
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
    ) {
        BottomBarGraph(
            navController = navController,
            paddingValues = it
        )
    }
}

private fun checkIfSelected(
    currentDestinationRoute: String?,
    currentBottomBarItem: String,
): Boolean {
    return if ((currentDestinationRoute?.contains("Home") == true || currentDestinationRoute?.contains(
            "Details"
        ) == true) && currentBottomBarItem == "Home"
    ) true
    else if (currentDestinationRoute?.contains(currentBottomBarItem) == true) true
    else false
}
