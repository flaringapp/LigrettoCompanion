package com.flaringapp.ligretto.feature.game.ui.end

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigationevent.NavigationEventInfo
import androidx.navigationevent.compose.NavigationBackHandler
import androidx.navigationevent.compose.rememberNavigationEventState
import com.flaringapp.ligretto.core.arch.ConsumeEffects
import com.flaringapp.ligretto.feature.game.ui.end.screen.GameEndScreenContent
import org.koin.compose.viewmodel.koinViewModel
import kotlinx.serialization.Serializable

@Serializable
internal data object GameEndDestination

@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun GameEndScreen(
    closeGame: () -> Unit,
    store: GameEndViewModel = koinViewModel(),
) {
    val state by store.observeState().collectAsStateWithLifecycle()

    ConsumeEffects(store.observeEffect()) { effect ->
        when (effect) {
            GameEndEffect.Close -> closeGame()
        }
    }

    NavigationBackHandler(
        state = rememberNavigationEventState(NavigationEventInfo.None),
        isBackEnabled = true,
    ) {
        store.dispatch(GameEndIntent.Finish)
    }

    GameEndScreenContent(
        state = state,
        dispatch = store::dispatch,
    )
}
