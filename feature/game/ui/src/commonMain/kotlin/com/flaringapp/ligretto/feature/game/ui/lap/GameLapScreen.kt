package com.flaringapp.ligretto.feature.game.ui.lap

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.flaringapp.ligretto.core.arch.AndroidBackHandler
import com.flaringapp.ligretto.core.arch.ConsumeEffects
import com.flaringapp.ligretto.feature.game.ui.lap.screen.GameLapScreenContent
import com.hoc081098.kmp.viewmodel.koin.compose.koinKmpViewModel

@Composable
internal fun GameLapScreen(
    openScores: () -> Unit,
    openClose: () -> Unit,
    openEnd: () -> Unit,
    store: GameLapViewModel = koinKmpViewModel(),
) {
    val state by store.observeState().collectAsStateWithLifecycle()

    ConsumeEffects(store.observeEffect()) { effect ->
        when (effect) {
            GameLapEffect.OpenScores -> openScores()
            GameLapEffect.EndGame -> openEnd()
        }
    }

    AndroidBackHandler(true) {
        openClose()
    }

    GameLapScreenContent(
        state = state,
        dispatch = store::dispatch,
    )
}
