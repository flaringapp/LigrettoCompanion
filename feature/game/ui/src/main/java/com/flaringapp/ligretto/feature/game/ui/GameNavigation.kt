package com.flaringapp.ligretto.feature.game.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import com.flaringapp.ligretto.core.navigation.ScreenDestinationWithoutArguments
import com.flaringapp.ligretto.core.navigation.composable
import com.flaringapp.ligretto.core.navigation.dialog
import com.flaringapp.ligretto.core.navigation.navigation
import com.flaringapp.ligretto.core.navigation.popUpTo
import com.flaringapp.ligretto.feature.game.ui.close.GameCloseDestination
import com.flaringapp.ligretto.feature.game.ui.close.GameCloseDialog
import com.flaringapp.ligretto.feature.game.ui.end.GameEndDestination
import com.flaringapp.ligretto.feature.game.ui.end.GameEndScreen
import com.flaringapp.ligretto.feature.game.ui.lap.GameLapDestination
import com.flaringapp.ligretto.feature.game.ui.lap.GameLapScreen
import com.flaringapp.ligretto.feature.game.ui.score.GameScoreDestination
import com.flaringapp.ligretto.feature.game.ui.score.GameScoreScreen
import com.flaringapp.ligretto.feature.game.ui.start.GameStartDestination
import com.flaringapp.ligretto.feature.game.ui.start.GameStartScreen

object GameDestination : ScreenDestinationWithoutArguments() {

    override val screenId: String = "game"
}

fun NavGraphBuilder.gameGraph(navController: NavController) {
    navigation(
        startDestination = GameStartDestination,
        destination = GameDestination,
    ) {
        composable(GameStartDestination) {
            GameStartScreen(
                openScore = navController::navigateGameScores,
                openClose = navController::navigateGameClose,
            )
        }
        composable(GameScoreDestination) {
            GameScoreScreen(
                openNextLap = navController::navigateGameLap,
                openClose = navController::navigateGameClose,
                openEnd = navController::navigateGameEnd,
            )
        }
        composable(GameLapDestination) {
            GameLapScreen(
                openScores = navController::navigateGameScores,
                openClose = navController::navigateGameClose,
                openEnd = navController::navigateGameEnd,
            )
        }
        dialog(GameCloseDestination) {
            GameCloseDialog(
                openEnd = navController::navigateGameEnd,
                closeGame = navController::navigateCloseGame,
                dismiss = { navController.popBackStack() }
            )
        }
        composable(GameEndDestination) {
            GameEndScreen(
                closeGame = navController::navigateCloseGame,
            )
        }
    }
}

private fun NavController.navigateGameScores() {
    navigate(GameScoreDestination.route()) {
        closeGameScreens()
    }
}

private fun NavController.navigateGameLap() {
    navigate(GameLapDestination.route()) {
        closeGameScreens()
    }
}

private fun NavController.navigateGameClose() {
    navigate(GameCloseDestination.route())
}

private fun NavController.navigateGameEnd() {
    navigate(GameEndDestination.route()) {
        closeGameScreens()
    }
}

private fun NavController.navigateCloseGame() {
    popBackStack(GameDestination.route(), true)
}

private fun NavOptionsBuilder.closeGameScreens() {
    popUpTo(GameDestination)
}
