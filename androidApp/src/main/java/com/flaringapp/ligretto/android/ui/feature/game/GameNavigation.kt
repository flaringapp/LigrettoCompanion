package com.flaringapp.ligretto.android.ui.feature.game

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import com.flaringapp.ligretto.android.ui.feature.game.close.GameCloseDestination
import com.flaringapp.ligretto.android.ui.feature.game.close.GameCloseDialog
import com.flaringapp.ligretto.android.ui.feature.game.end.GameEndDestination
import com.flaringapp.ligretto.android.ui.feature.game.end.GameEndScreen
import com.flaringapp.ligretto.android.ui.feature.game.lap.GameLapDestination
import com.flaringapp.ligretto.android.ui.feature.game.lap.GameLapScreen
import com.flaringapp.ligretto.android.ui.feature.game.score.GameScoreDestination
import com.flaringapp.ligretto.android.ui.feature.game.score.GameScoreScreen
import com.flaringapp.ligretto.android.ui.feature.game.start.GameStartDestination
import com.flaringapp.ligretto.android.ui.feature.game.start.GameStartScreen
import com.flaringapp.ligretto.core.navigation.ScreenDestinationWithoutArguments
import com.flaringapp.ligretto.core.navigation.composable
import com.flaringapp.ligretto.core.navigation.dialog
import com.flaringapp.ligretto.core.navigation.navigation
import com.flaringapp.ligretto.core.navigation.popUpTo

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
