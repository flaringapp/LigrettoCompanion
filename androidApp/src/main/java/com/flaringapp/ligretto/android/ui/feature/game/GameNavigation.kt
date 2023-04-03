package com.flaringapp.ligretto.android.ui.feature.game

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import com.flaringapp.ligretto.android.ui.feature.game.end.GameEndDestination
import com.flaringapp.ligretto.android.ui.feature.game.end.GameEndScreen
import com.flaringapp.ligretto.android.ui.feature.game.lap.GameLapDestination
import com.flaringapp.ligretto.android.ui.feature.game.lap.GameLapScreen
import com.flaringapp.ligretto.android.ui.feature.game.score.GameScoreDestination
import com.flaringapp.ligretto.android.ui.feature.game.score.GameScoreScreen
import com.flaringapp.ligretto.android.ui.feature.game.start.GameStartDestination
import com.flaringapp.ligretto.android.ui.feature.game.start.GameStartScreen
import com.flaringapp.ligretto.android.ui.utils.navigation.ScreenDestinationWithoutArguments
import com.flaringapp.ligretto.android.ui.utils.navigation.composable
import com.flaringapp.ligretto.android.ui.utils.navigation.navigation
import com.flaringapp.ligretto.android.ui.utils.navigation.popUpTo

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
                openScores = navController::navigateGameScores,
            )
        }
        composable(GameScoreDestination) {
            GameScoreScreen(
                openNextLap = navController::navigateGameLap,
            )
        }
        composable(GameLapDestination) {
            GameLapScreen(
                openScores = navController::navigateGameScores,
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
