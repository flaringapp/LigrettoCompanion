package com.flaringapp.ligretto.feature.home.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.flaringapp.ligretto.core.navigation.ScreenDestinationWithoutArguments
import com.flaringapp.ligretto.core.navigation.composableDestination
import com.flaringapp.ligretto.core.navigation.dialogDestination
import com.flaringapp.ligretto.core.navigation.navigationDestination
import com.flaringapp.ligretto.feature.home.ui.game_ended.GameEndedDestination
import com.flaringapp.ligretto.feature.home.ui.game_ended.GameEndedDialog
import com.flaringapp.ligretto.feature.home.ui.home.HomeScreen
import com.flaringapp.ligretto.feature.home.ui.home.HomeScreenDestination

object HomeDestination : ScreenDestinationWithoutArguments() {

    override val screenId: String = "home"
}

fun NavGraphBuilder.homeGraph(
    navController: NavController,
    startGame: (restartLastGame: Boolean) -> Unit,
    resumeGame: (openLap: Boolean) -> Unit,
) {
    navigationDestination(
        startDestination = HomeScreenDestination,
        destination = HomeDestination,
    ) {
        composableDestination(HomeScreenDestination) {
            HomeScreen(
                openStartGame = startGame,
                openResumeGame = resumeGame,
                openActiveGameEnded = navController::openGameEnded,
            )
        }
        dialogDestination(GameEndedDestination) {
            GameEndedDialog(
                dismiss = navController::navigateUp,
            )
        }
    }
}

private fun NavController.openGameEnded() {
    navigate(GameEndedDestination.route())
}
