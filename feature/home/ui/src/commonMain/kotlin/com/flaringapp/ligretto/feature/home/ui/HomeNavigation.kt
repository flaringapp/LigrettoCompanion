package com.flaringapp.ligretto.feature.home.ui

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.scene.DialogSceneStrategy
import com.flaringapp.ligretto.feature.home.ui.game_ended.GameEndedDialog
import com.flaringapp.ligretto.feature.home.ui.home.HomeScreen

fun EntryProviderScope<NavKey>.homeGraph(
    backStack: NavBackStack<NavKey>,
    startGame: (restartLastGame: Boolean) -> Unit,
    resumeGame: (openLap: Boolean) -> Unit,
) {
    entry<HomeScreenDestination> {
        HomeScreen(
            openStartGame = startGame,
            openResumeGame = resumeGame,
            openActiveGameEnded = { backStack.add(GameEndedDestination) },
        )
    }
    entry<GameEndedDestination>(
        metadata = DialogSceneStrategy.dialog(),
    ) {
        GameEndedDialog(
            dismiss = backStack::removeLastOrNull,
        )
    }
}
