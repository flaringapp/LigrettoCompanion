package com.flaringapp.ligretto.feature.game.ui

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.flaringapp.ligretto.core.navigation.ScreenDestination
import com.flaringapp.ligretto.core.navigation.composableDestination
import com.flaringapp.ligretto.core.navigation.dialogDestination
import com.flaringapp.ligretto.core.navigation.navigationDestination
import com.flaringapp.ligretto.core.navigation.popUpToDestination
import com.flaringapp.ligretto.feature.game.ui.close.GameCloseDestination
import com.flaringapp.ligretto.feature.game.ui.close.GameCloseDialog
import com.flaringapp.ligretto.feature.game.ui.end.GameEndDestination
import com.flaringapp.ligretto.feature.game.ui.end.GameEndScreen
import com.flaringapp.ligretto.feature.game.ui.lap.cardsleft.GameLapCardsLeftDestination
import com.flaringapp.ligretto.feature.game.ui.lap.cardsleft.GameLapCardsLeftScreen
import com.flaringapp.ligretto.feature.game.ui.lap.cardsontable.GameLapCardsOnTableDestination
import com.flaringapp.ligretto.feature.game.ui.lap.cardsontable.GameLapCardsOnTableScreen
import com.flaringapp.ligretto.feature.game.ui.score.GameScoreDestination
import com.flaringapp.ligretto.feature.game.ui.score.GameScoreScreen
import com.flaringapp.ligretto.feature.game.ui.start.GameStartDestination
import com.flaringapp.ligretto.feature.game.ui.start.GameStartScreen

internal object GameDestination : ScreenDestination {

    override val screenId: String = "game"

    override val route: String = "$screenId?restart_last_game={restart_last_game}"

    override val arguments: List<NamedNavArgument> = listOf(
        navArgument("restart_last_game") {
            type = NavType.BoolType
            defaultValue = false
        },
    )

    fun route(restartLastGame: Boolean): String {
        return "$screenId?restart_last_game=$restartLastGame"
    }

    fun restartLastGame(entry: NavBackStackEntry): Boolean {
        return entry.arguments!!.getBoolean("restart_last_game")
    }
}

fun NavGraphBuilder.gameGraph(navController: NavController) {
    navigationDestination(
        startDestination = GameStartDestination,
        destination = GameDestination,
    ) {
        composableDestination(GameStartDestination) {
            GameStartScreen(
                restartLastGame = GameDestination.restartLastGame(it),
                openScore = navController::navigateGameScores,
                openClose = navController::navigateGameClose,
            )
        }
        composableDestination(GameScoreDestination) {
            GameScoreScreen(
                openNextLap = navController::navigateGameLapCardsLeft,
                openClose = navController::navigateGameClose,
                openEnd = navController::navigateGameEnd,
            )
        }
        composableDestination(GameLapCardsLeftDestination) {
            GameLapCardsLeftScreen(
                openCardsOnTable = navController::navigateGameLapCardsOnTable,
                openClose = navController::navigateGameClose,
            )
        }
        composableDestination(GameLapCardsOnTableDestination) {
            GameLapCardsOnTableScreen(
                openCardsLeft = navController::navigateUp,
                openScores = navController::navigateGameScores,
                openEnd = navController::navigateGameEnd,
            )
        }
        dialogDestination(GameCloseDestination) {
            GameCloseDialog(
                openEnd = navController::navigateGameEnd,
                closeGame = navController::navigateCloseGame,
                dismiss = navController::navigateUp,
            )
        }
        composableDestination(GameEndDestination) {
            GameEndScreen(
                closeGame = navController::navigateCloseGame,
            )
        }
    }
}

fun NavController.navigateNewGame(restartLastGame: Boolean) {
    navigate(
        GameDestination.route(restartLastGame = restartLastGame),
    )
}

fun NavController.navigateResumeGame(openLap: Boolean) {
    navigateNewGame(restartLastGame = false)

    if (openLap) {
        navigateGameLapCardsLeft()
    } else {
        navigateGameScores()
    }
}

private fun NavController.navigateGameScores() {
    navigate(GameScoreDestination.route()) {
        closeGameScreens()
    }
}

private fun NavController.navigateGameLapCardsLeft() {
    navigate(GameLapCardsLeftDestination.route()) {
        closeGameScreens()
    }
}

private fun NavController.navigateGameLapCardsOnTable() {
    navigate(GameLapCardsOnTableDestination.route())
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
    popBackStack(GameDestination.route, true)
}

private fun NavOptionsBuilder.closeGameScreens() {
    popUpToDestination(GameDestination)
}
