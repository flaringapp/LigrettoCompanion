package com.flaringapp.ligretto.android.main

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.flaringapp.ligretto.feature.game.ui.GameDestination
import com.flaringapp.ligretto.feature.game.ui.gameGraph
import com.flaringapp.ligretto.feature.home.ui.homeGraph

fun NavGraphBuilder.appNavGraph(navController: NavController) {
    homeGraph(
        navController = navController,
        startGame = navController::navigateNewGame,
    )
    gameGraph(
        navController = navController,
    )
}

private fun NavController.navigateNewGame(restartLastGame: Boolean) {
    navigate(
        GameDestination.route(restartLastGame = restartLastGame)
    )
}
