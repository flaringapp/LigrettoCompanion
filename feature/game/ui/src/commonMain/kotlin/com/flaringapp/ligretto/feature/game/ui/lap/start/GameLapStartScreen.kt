package com.flaringapp.ligretto.feature.game.ui.lap.start

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.backhandler.BackHandler
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.flaringapp.ligretto.core.arch.ConsumeEffects
import com.flaringapp.ligretto.feature.game.ui.lap.start.screen.GameLapStartScreenContent
import org.koin.compose.viewmodel.koinViewModel
import kotlinx.serialization.Serializable

@Serializable
internal data object GameLapStartDestination

@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun GameLapStartScreen(
    openLap: () -> Unit,
    store: GameLapStartViewModel = koinViewModel(),
) {
    val state by store.observeState().collectAsStateWithLifecycle()

    ConsumeEffects(store.observeEffect()) { effect ->
        when (effect) {
            GameLapStartEffect.OpenLap -> openLap()
        }
    }

    BackHandler(true) {}

    GameLapStartScreenContent(
        state = state,
        dispatch = store::dispatch,
    )
}
