package com.flaringapp.ligretto.android.ui.main

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.flaringapp.ligretto.android.ui.feature.game.GameDestination
import com.flaringapp.ligretto.android.ui.feature.game.gameGraph
import com.flaringapp.ligretto.android.ui.feature.home.HomeDestination
import com.flaringapp.ligretto.android.ui.feature.home.HomeScreen
import com.flaringapp.ligretto.core.navigation.composable

fun NavGraphBuilder.appNavGraph(navController: NavController) {
    composable(HomeDestination) {
        HomeScreen(
            startGame = navController::navigateNewGame,
        )
    }
    gameGraph(navController)
}

private fun NavController.navigateNewGame() {
    navigate(GameDestination.route())
}
