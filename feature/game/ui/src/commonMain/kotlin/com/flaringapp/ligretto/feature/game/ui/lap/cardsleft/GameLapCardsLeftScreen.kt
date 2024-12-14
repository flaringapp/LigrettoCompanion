package com.flaringapp.ligretto.feature.game.ui.lap.cardsleft

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.flaringapp.ligretto.core.arch.AndroidBackHandler
import com.flaringapp.ligretto.core.arch.ConsumeEffects
import com.flaringapp.ligretto.feature.game.ui.lap.cardsleft.screen.GameLapCardsLeftScreenContent
import org.koin.compose.viewmodel.koinViewModel

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

    AndroidBackHandler(true) {
        openClose()
    }

    GameLapCardsLeftScreenContent(
        state = state,
        dispatch = store::dispatch,
        onChangeSettings = openSettings,
        onFinish = openClose,
    )
}
