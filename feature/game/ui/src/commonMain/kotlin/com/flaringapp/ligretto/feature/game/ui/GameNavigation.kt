package com.flaringapp.ligretto.feature.game.ui

import androidx.compose.ui.window.DialogProperties
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.scene.DialogSceneStrategy
import com.flaringapp.ligretto.core.navigation.LigrettoNavBackStack
import com.flaringapp.ligretto.feature.game.ui.close.GameCloseDialog
import com.flaringapp.ligretto.feature.game.ui.end.GameEndScreen
import com.flaringapp.ligretto.feature.game.ui.lap.cardsleft.GameLapCardsLeftScreen
import com.flaringapp.ligretto.feature.game.ui.lap.cardsontable.GameLapCardsOnTableScreen
import com.flaringapp.ligretto.feature.game.ui.lap.start.GameLapStartScreen
import com.flaringapp.ligretto.feature.game.ui.score.GameScoreScreen
import com.flaringapp.ligretto.feature.game.ui.settings.GameSettingsDialog
import com.flaringapp.ligretto.feature.game.ui.start.GameStartScreen

fun EntryProviderScope<NavKey>.gameGraph(backStack: LigrettoNavBackStack) {
    entry<GameStartDestination> { key ->
        GameStartScreen(
            restartLastGame = key.restartLastGame,
            openGame = backStack::navigateGameLapStart,
            openClose = backStack::navigateGameClose,
        )
    }
    entry<GameScoreDestination> {
        GameScoreScreen(
            openNextLap = backStack::navigateGameLapStart,
            openSettings = backStack::navigateGameSettings,
            openClose = backStack::navigateGameClose,
            openEnd = backStack::navigateGameEnd,
        )
    }
    entry<GameLapStartDestination> {
        GameLapStartScreen(
            openLap = backStack::navigateGameLapCardsLeft,
        )
    }
    entry<GameLapCardsLeftDestination> {
        GameLapCardsLeftScreen(
            openCardsOnTable = backStack::navigateGameLapCardsOnTable,
            openSettings = backStack::navigateGameSettings,
            openClose = backStack::navigateGameClose,
        )
    }
    entry<GameLapCardsOnTableDestination> {
        GameLapCardsOnTableScreen(
            navigateBack = backStack::removeLastOrNull,
            openScores = backStack::navigateGameScores,
            openEnd = backStack::navigateGameEnd,
            openSettings = backStack::navigateGameSettings,
            openClose = backStack::navigateGameClose,
        )
    }
    entry<GameSettingsDestination>(
        metadata = DialogSceneStrategy.dialog(
            dialogProperties = DialogProperties(
                dismissOnClickOutside = false,
            ),
        ),
    ) {
        GameSettingsDialog(
            close = backStack::removeLastOrNull,
        )
    }
    entry<GameCloseDestination>(
        metadata = DialogSceneStrategy.dialog(),
    ) {
        GameCloseDialog(
            openEnd = backStack::navigateGameEnd,
            closeGame = backStack::closeGame,
            dismiss = backStack::removeLastOrNull,
        )
    }
    entry<GameEndDestination> {
        GameEndScreen(
            closeGame = backStack::closeGame,
        )
    }
}

fun LigrettoNavBackStack.navigateNewGame(restartLastGame: Boolean) {
    replaceGameScreen(
        GameStartDestination(restartLastGame = restartLastGame),
    )
}

fun LigrettoNavBackStack.navigateResumeGame(openLap: Boolean) {
    replaceGameScreen(
        if (openLap) {
            GameLapCardsLeftDestination
        } else {
            GameScoreDestination
        },
    )
}

private fun LigrettoNavBackStack.navigateGameScores() {
    replaceGameScreen(GameScoreDestination)
}

private fun LigrettoNavBackStack.navigateGameLapStart() {
    replaceGameScreen(GameLapStartDestination)
}

private fun LigrettoNavBackStack.navigateGameLapCardsLeft() {
    replaceGameScreen(GameLapCardsLeftDestination)
}

private fun LigrettoNavBackStack.navigateGameLapCardsOnTable() {
    add(GameLapCardsOnTableDestination)
}

private fun LigrettoNavBackStack.navigateGameSettings() {
    add(GameSettingsDestination)
}

private fun LigrettoNavBackStack.navigateGameClose() {
    add(GameCloseDestination)
}

private fun LigrettoNavBackStack.navigateGameEnd() {
    replaceGameScreen(GameEndDestination)
}

private fun LigrettoNavBackStack.replaceGameScreen(destination: GameNavDestination) {
    closeGame()
    add(destination)
}

private fun LigrettoNavBackStack.closeGame() {
    removeAll { it is GameNavDestination }
}
