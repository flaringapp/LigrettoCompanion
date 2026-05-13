package com.flaringapp.ligretto.feature.game.ui

import androidx.compose.ui.window.DialogProperties
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.scene.DialogSceneStrategy
import com.flaringapp.ligretto.feature.game.ui.close.GameCloseDialog
import com.flaringapp.ligretto.feature.game.ui.end.GameEndScreen
import com.flaringapp.ligretto.feature.game.ui.lap.cardsleft.GameLapCardsLeftScreen
import com.flaringapp.ligretto.feature.game.ui.lap.cardsontable.GameLapCardsOnTableScreen
import com.flaringapp.ligretto.feature.game.ui.lap.start.GameLapStartScreen
import com.flaringapp.ligretto.feature.game.ui.score.GameScoreScreen
import com.flaringapp.ligretto.feature.game.ui.settings.GameSettingsDialog
import com.flaringapp.ligretto.feature.game.ui.start.GameStartScreen

fun EntryProviderScope<NavKey>.gameGraph(backStack: NavBackStack<NavKey>) {
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

fun NavBackStack<NavKey>.navigateNewGame(restartLastGame: Boolean) {
    replaceGameScreen(
        GameStartDestination(restartLastGame = restartLastGame),
    )
}

fun NavBackStack<NavKey>.navigateResumeGame(openLap: Boolean) {
    replaceGameScreen(
        if (openLap) {
            GameLapCardsLeftDestination
        } else {
            GameScoreDestination
        },
    )
}

private fun NavBackStack<NavKey>.navigateGameScores() {
    replaceGameScreen(GameScoreDestination)
}

private fun NavBackStack<NavKey>.navigateGameLapStart() {
    replaceGameScreen(GameLapStartDestination)
}

private fun NavBackStack<NavKey>.navigateGameLapCardsLeft() {
    replaceGameScreen(GameLapCardsLeftDestination)
}

private fun NavBackStack<NavKey>.navigateGameLapCardsOnTable() {
    add(GameLapCardsOnTableDestination)
}

private fun NavBackStack<NavKey>.navigateGameSettings() {
    add(GameSettingsDestination)
}

private fun NavBackStack<NavKey>.navigateGameClose() {
    add(GameCloseDestination)
}

private fun NavBackStack<NavKey>.navigateGameEnd() {
    replaceGameScreen(GameEndDestination)
}

private fun NavBackStack<NavKey>.replaceGameScreen(destination: GameNavDestination) {
    closeGame()
    add(destination)
}

private fun NavBackStack<NavKey>.closeGame() {
    removeAll { it is GameNavDestination }
}
