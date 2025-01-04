package com.flaringapp.ligretto.feature.home.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.navigation
import com.flaringapp.ligretto.feature.home.ui.game_ended.GameEndedDestination
import com.flaringapp.ligretto.feature.home.ui.game_ended.GameEndedDialog
import com.flaringapp.ligretto.feature.home.ui.home.HomeScreen
import com.flaringapp.ligretto.feature.home.ui.home.HomeScreenDestination
import kotlinx.serialization.Serializable

@Serializable
data object HomeDestination

fun NavGraphBuilder.homeGraph(
    navController: NavController,
    startGame: (restartLastGame: Boolean) -> Unit,
    resumeGame: (openLap: Boolean) -> Unit,
) {
    navigation<HomeDestination>(
        startDestination = HomeScreenDestination,
    ) {
        composable<HomeScreenDestination> {
            HomeScreen(
                openStartGame = startGame,
                openResumeGame = resumeGame,
                openActiveGameEnded = navController::openGameEnded,
            )
        }
        dialog<GameEndedDestination> {
            GameEndedDialog(
                dismiss = navController::navigateUp,
            )
        }
    }
}

private fun NavController.openGameEnded() {
    navigate(GameEndedDestination)
}
