package com.flaringapp.ligretto.android.ui.main

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.flaringapp.ligretto.android.ui.feature.game.GameDestination
import com.flaringapp.ligretto.android.ui.feature.game.gameGraph
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

private fun NavController.navigateNewGame() {
    navigate(GameDestination.route())
}
