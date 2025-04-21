package com.flaringapp.ligretto.feature.game.ui

import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.flaringapp.ligretto.feature.game.ui.close.GameCloseDestination
import com.flaringapp.ligretto.feature.game.ui.close.GameCloseDialog
import com.flaringapp.ligretto.feature.game.ui.end.GameEndDestination
import com.flaringapp.ligretto.feature.game.ui.end.GameEndScreen
import com.flaringapp.ligretto.feature.game.ui.lap.cardsleft.GameLapCardsLeftDestination
import com.flaringapp.ligretto.feature.game.ui.lap.cardsleft.GameLapCardsLeftScreen
import com.flaringapp.ligretto.feature.game.ui.lap.cardsontable.GameLapCardsOnTableDestination
import com.flaringapp.ligretto.feature.game.ui.lap.cardsontable.GameLapCardsOnTableScreen
import com.flaringapp.ligretto.feature.game.ui.lap.start.GameLapStartDestination
import com.flaringapp.ligretto.feature.game.ui.lap.start.GameLapStartScreen
import com.flaringapp.ligretto.feature.game.ui.score.GameScoreDestination
import com.flaringapp.ligretto.feature.game.ui.score.GameScoreScreen
import com.flaringapp.ligretto.feature.game.ui.settings.GameSettingsDestination
import com.flaringapp.ligretto.feature.game.ui.settings.GameSettingsDialog
import com.flaringapp.ligretto.feature.game.ui.start.GameStartDestination
import com.flaringapp.ligretto.feature.game.ui.start.GameStartScreen
import kotlinx.serialization.Serializable

@Serializable
data class GameDestination(
    val restartLastGame: Boolean,
)

fun NavGraphBuilder.gameGraph(navController: NavController) {
    navigation<GameDestination>(
        startDestination = GameStartDestination::class,
    ) {
        composable<GameStartDestination> {
            GameStartScreen(
                restartLastGame = it.toRoute<GameStartDestination>().restartLastGame,
                openGame = navController::navigateGameLapStart,
                openClose = navController::navigateGameClose,
            )
        }
        composable<GameScoreDestination> {
            GameScoreScreen(
                openNextLap = navController::navigateGameLapStart,
                openSettings = navController::navigateGameSettings,
                openClose = navController::navigateGameClose,
                openEnd = navController::navigateGameEnd,
            )
        }
        composable<GameLapStartDestination> {
            GameLapStartScreen(
                openLap = navController::navigateGameLapCardsLeft,
            )
        }
        composable<GameLapCardsLeftDestination> {
            GameLapCardsLeftScreen(
                openCardsOnTable = navController::navigateGameLapCardsOnTable,
                openSettings = navController::navigateGameSettings,
                openClose = navController::navigateGameClose,
            )
        }
        composable<GameLapCardsOnTableDestination> {
            GameLapCardsOnTableScreen(
                navigateBack = navController::navigateUp,
                openScores = navController::navigateGameScores,
                openEnd = navController::navigateGameEnd,
                openSettings = navController::navigateGameSettings,
                openClose = navController::navigateGameClose,
            )
        }
        dialog<GameSettingsDestination>(
            dialogProperties = DialogProperties(
                dismissOnClickOutside = false,
            ),
        ) {
            GameSettingsDialog(
                close = navController::navigateUp,
            )
        }
        dialog<GameCloseDestination> {
            GameCloseDialog(
                openEnd = navController::navigateGameEnd,
                closeGame = navController::navigateCloseGame,
                dismiss = navController::navigateUp,
            )
        }
        composable<GameEndDestination> {
            GameEndScreen(
                closeGame = navController::navigateCloseGame,
            )
        }
    }
}

fun NavController.navigateNewGame(restartLastGame: Boolean) {
    navigate(
        GameDestination(restartLastGame = restartLastGame),
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
    navigate(GameScoreDestination) {
        closeGameScreens()
    }
}

private fun NavController.navigateGameLapStart() {
    navigate(GameLapStartDestination) {
        closeGameScreens()
    }
}

private fun NavController.navigateGameLapCardsLeft() {
    navigate(GameLapCardsLeftDestination) {
        closeGameScreens()
    }
}

private fun NavController.navigateGameLapCardsOnTable() {
    navigate(GameLapCardsOnTableDestination)
}

private fun NavController.navigateGameSettings() {
    navigate(GameSettingsDestination)
}

private fun NavController.navigateGameClose() {
    navigate(GameCloseDestination)
}

private fun NavController.navigateGameEnd() {
    navigate(GameEndDestination) {
        closeGameScreens()
    }
}

private fun NavController.navigateCloseGame() {
    popBackStack<GameDestination>(inclusive = true)
}

private fun NavOptionsBuilder.closeGameScreens() {
    popUpTo<GameDestination>()
}
