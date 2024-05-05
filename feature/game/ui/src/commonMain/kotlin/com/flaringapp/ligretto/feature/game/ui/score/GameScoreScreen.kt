package com.flaringapp.ligretto.feature.game.ui.score

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.flaringapp.ligretto.core.arch.AndroidBackHandler
import com.flaringapp.ligretto.core.arch.ConsumeEffects
import com.flaringapp.ligretto.core.arch.koin.koinViewModel
import com.flaringapp.ligretto.feature.game.ui.score.screen.GameScoreScreenContent

@Composable
internal fun GameScoreScreen(
    openNextLap: () -> Unit,
    openClose: () -> Unit,
    openEnd: () -> Unit,
    store: GameScoreViewModel = koinViewModel(),
) {
    val state by store.observeState().collectAsStateWithLifecycle()

    ConsumeEffects(store.observeEffect()) { effect ->
        when (effect) {
            GameScoreEffect.OpenNextLap -> openNextLap()
            GameScoreEffect.EndGame -> openEnd()
        }
    }

    AndroidBackHandler(true) {
        openClose()
    }

    GameScoreScreenContent(
        state = state,
        dispatch = store::dispatch,
    )
}
