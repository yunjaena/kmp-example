package dev.yunzai.demo.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.yunzai.demo.domain.Person
import dev.yunzai.demo.presentation.root.RootScreen
import dev.yunzai.demo.presentation.settings.SettingsScreen
import kotlinx.serialization.json.Json

@Composable
fun RootNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Root,
        enterTransition = { slideInHorizontally { it } },
        exitTransition = { slideOutHorizontally { -it } },
        popEnterTransition = { slideInHorizontally { -it } },
        popExitTransition = { slideOutHorizontally { it } }
    ) {
        composable<Screen.Root> {
            RootScreen(
                navigateToSettings = {
                    navController.navigate(Screen.Settings)
                }
            )
        }
        composable<Screen.Settings>(
            enterTransition = { slideInVertically { it } + fadeIn() },
            exitTransition = { slideOutVertically { it } + fadeOut() }
        ) {
            SettingsScreen(
                navigateBack = { navController.navigateUp() }
            )
        }
    }
}

fun String.deserialize(): Person = Json.decodeFromString(this)
