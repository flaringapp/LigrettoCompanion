package com.flaringapp.ligretto.feature.game.ui.start

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigationevent.NavigationEventInfo
import androidx.navigationevent.compose.NavigationBackHandler
import androidx.navigationevent.compose.rememberNavigationEventState
import com.flaringapp.ligretto.core.arch.ConsumeEffects
import com.flaringapp.ligretto.feature.game.ui.start.screen.GameStartScreenContent
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf
import kotlinx.serialization.Serializable

@Serializable
internal data class GameStartDestination(
    val restartLastGame: Boolean,
)

@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun GameStartScreen(
    restartLastGame: Boolean,
    openGame: () -> Unit,
    openClose: () -> Unit,
    store: GameStartViewModel = koinViewModel { parametersOf(restartLastGame) },
) {
    val state by store.observeState().collectAsStateWithLifecycle()

    ConsumeEffects(store.observeEffect()) { effect ->
        when (effect) {
            GameStartEffect.StartGame -> openGame()
        }
    }

    NavigationBackHandler(
        state = rememberNavigationEventState(NavigationEventInfo.None),
        isBackEnabled = true,
    ) {
        openClose()
    }

    GameStartScreenContent(
        state = state,
        dispatch = store::dispatch,
        close = openClose,
    )
}
