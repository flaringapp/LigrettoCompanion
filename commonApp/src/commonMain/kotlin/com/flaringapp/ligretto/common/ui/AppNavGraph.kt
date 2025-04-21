package com.flaringapp.ligretto.common.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.flaringapp.ligretto.feature.game.ui.gameGraph
import com.flaringapp.ligretto.feature.game.ui.navigateNewGame
import com.flaringapp.ligretto.feature.game.ui.navigateResumeGame
import com.flaringapp.ligretto.feature.home.ui.homeGraph

internal fun NavGraphBuilder.appNavGraph(navController: NavController) {
    homeGraph(
        navController = navController,
        startGame = navController::navigateNewGame,
        resumeGame = navController::navigateResumeGame,
    )
    gameGraph(
        navController = navController,
    )
}
