package dev.yunzai.demo.domain

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import dev.yunzai.demo.navigation.Screen

sealed class BottomBarDestination(
    val screen: Screen,
    val icon: ImageVector,
) {
    data object Home : BottomBarDestination(
        screen = Screen.Home,
        icon = Icons.Default.Home
    )

    data object Profile : BottomBarDestination(
        screen = Screen.Profile,
        icon = Icons.Default.Person
    )
}

val bottomBarDestinations = listOf(
    BottomBarDestination.Home,
    BottomBarDestination.Profile
)
