package com.flaringapp.ligretto.feature.game.ui.end

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.flaringapp.ligretto.core.arch.AndroidBackHandler
import com.flaringapp.ligretto.core.arch.ConsumeEffects
import com.flaringapp.ligretto.feature.game.ui.end.screen.GameEndScreenContent
import com.hoc081098.kmp.viewmodel.koin.compose.koinKmpViewModel

@Composable
internal fun GameEndScreen(
    closeGame: () -> Unit,
    store: GameEndViewModel = koinKmpViewModel(),
) {
    val state by store.observeState().collectAsStateWithLifecycle()

    ConsumeEffects(store.observeEffect()) { effect ->
        when (effect) {
            GameEndEffect.Close -> closeGame()
        }
    }

    AndroidBackHandler(true) {
        store.dispatch(GameEndIntent.Finish)
    }

    GameEndScreenContent(
        state = state,
        dispatch = store::dispatch,
    )
}
