package com.flaringapp.ligretto.feature.game.ui.lap.cardsleft

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigationevent.NavigationEventInfo
import androidx.navigationevent.compose.NavigationBackHandler
import androidx.navigationevent.compose.rememberNavigationEventState
import com.flaringapp.ligretto.core.arch.ConsumeEffects
import com.flaringapp.ligretto.feature.game.ui.lap.cardsleft.screen.GameLapCardsLeftScreenContent
import org.koin.compose.viewmodel.koinViewModel
import kotlinx.serialization.Serializable

@Serializable
internal data object GameLapCardsLeftDestination

@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun GameLapCardsLeftScreen(
    openCardsOnTable: () -> Unit,
    openSettings: () -> Unit,
    openClose: () -> Unit,
    store: GameLapCardsLeftViewModel = koinViewModel(),
) {
    val state by store.observeState().collectAsStateWithLifecycle()

    ConsumeEffects(store.observeEffect()) { effect ->
        when (effect) {
            GameLapCardsLeftEffect.OpenCardsOnTable -> openCardsOnTable()
        }
    }

    NavigationBackHandler(
        state = rememberNavigationEventState(NavigationEventInfo.None),
        isBackEnabled = true,
    ) {
        openClose()
    }

    GameLapCardsLeftScreenContent(
        state = state,
        dispatch = store::dispatch,
        onChangeSettings = openSettings,
        onFinish = openClose,
    )
}
