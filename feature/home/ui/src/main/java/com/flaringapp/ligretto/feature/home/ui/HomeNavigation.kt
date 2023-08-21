package com.flaringapp.ligretto.feature.home.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.flaringapp.ligretto.core.navigation.ScreenDestinationWithoutArguments
import com.flaringapp.ligretto.core.navigation.composable
import com.flaringapp.ligretto.core.navigation.dialog
import com.flaringapp.ligretto.core.navigation.navigation
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
    navigation(
        startDestination = HomeScreenDestination,
        destination = HomeDestination,
    ) {
        composable(HomeScreenDestination) {
            HomeScreen(
                openStartGame = startGame,
                openResumeGame = resumeGame,
                openActiveGameEnded = navController::openGameEnded,
            )
        }
        dialog(GameEndedDestination) {
            GameEndedDialog(
                dismiss = { navController.popBackStack() },
            )
        }
    }
}

private fun NavController.openGameEnded() {
    navigate(GameEndedDestination.route())
}
