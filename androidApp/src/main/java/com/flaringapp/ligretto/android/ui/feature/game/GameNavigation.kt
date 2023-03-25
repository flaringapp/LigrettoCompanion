package com.flaringapp.ligretto.android.ui.feature.game

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
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
                openEnd = navController::navigateGameEnd,
            )
        }
        composable(GameLapDestination) {
            GameLapScreen(
                openScores = navController::navigateGameScores,
                openEnd = navController::navigateGameEnd,
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
    navigate(GameScoreDestination.route())
}

private fun NavController.navigateGameLap() {
    navigate(GameLapDestination.route())
}

private fun NavController.navigateGameEnd() {
    navigate(GameEndDestination.route())
}

private fun NavController.navigateCloseGame() {
    popBackStack(GameDestination.route(), true)
}
