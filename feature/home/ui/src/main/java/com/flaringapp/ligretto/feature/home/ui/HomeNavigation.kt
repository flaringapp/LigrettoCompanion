package com.flaringapp.ligretto.feature.home.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.flaringapp.ligretto.core.navigation.ScreenDestinationWithoutArguments
import com.flaringapp.ligretto.core.navigation.composable
import com.flaringapp.ligretto.core.navigation.navigation

object HomeDestination : ScreenDestinationWithoutArguments() {

    override val screenId: String = "home"
}

@Suppress("UNUSED_PARAMETER")
fun NavGraphBuilder.homeGraph(
    navController: NavController,
    startGame: (restartLastGame: Boolean) -> Unit,
) {
    navigation(
        startDestination = HomeScreenDestination,
        destination = HomeDestination,
    ) {
        composable(HomeScreenDestination) {
            HomeScreen(
                openStartGame = startGame,
            )
        }
    }
}
