package dev.yunzai.demo.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import dev.yunzai.demo.domain.Person
import dev.yunzai.demo.presentation.root.bottom_bar.home.HomeScreen
import dev.yunzai.demo.presentation.root.bottom_bar.home.details.DetailsScreen
import dev.yunzai.demo.presentation.root.bottom_bar.profile.ProfileScreen
import kotlin.random.Random

@Composable
fun BottomBarGraph(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        modifier = Modifier
            .padding(
                top =  paddingValues.calculateTopPadding(),
                bottom = paddingValues.calculateBottomPadding()
            ),
        navController = navController,
        startDestination = Screen.BottomBarGraph
    ) {
        navigation<Screen.BottomBarGraph>(
            startDestination = Screen.Home
        ) {
            composable<Screen.Home> {
                HomeScreen(
                    navigateToDetails = {
                        navController.navigate(
                            Screen.Details(
                                id = Random.nextInt(0, 100),
                                person = Person(
                                    name = "John",
                                    age = 30,
                                    address = "Dummy street"
                                ).serialize()
                            )
                        )
                    }
                )
            }
            composable<Screen.Details> {
                val id = it.toRoute<Screen.Details>().id
                DetailsScreen(
                    id = id
                )
            }

            composable<Screen.Profile> {
                ProfileScreen()
            }
        }
    }
}
