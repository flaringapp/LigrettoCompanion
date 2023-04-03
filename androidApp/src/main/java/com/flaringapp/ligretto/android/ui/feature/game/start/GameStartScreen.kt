package com.flaringapp.ligretto.android.ui.feature.game.start

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.flaringapp.ligretto.android.ui.feature.game.start.screen.GameStartScreenContent
import com.flaringapp.ligretto.android.ui.mvi.ConsumeEffects
import com.flaringapp.ligretto.android.ui.utils.navigation.ScreenDestinationWithoutArguments
import org.koin.androidx.compose.getViewModel

object GameStartDestination : ScreenDestinationWithoutArguments() {

    override val screenId: String = "game/start"
}

@Composable
fun GameStartScreen(
    openScore: () -> Unit,
    openClose: () -> Unit,
    store: GameStartViewModel = getViewModel(),
) {
    val state by store.observeState().collectAsState()

    ConsumeEffects(store.observeEffect()) { effect ->
        when (effect) {
            GameStartEffect.StartGame -> openScore()
        }
    }

    BackHandler(true) {
        openClose()
    }

    GameStartScreenContent(
        state = state,
        dispatch = store::dispatch,
    )
}
