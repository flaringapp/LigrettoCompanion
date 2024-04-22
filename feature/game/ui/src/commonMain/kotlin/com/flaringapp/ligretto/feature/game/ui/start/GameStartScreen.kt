package com.flaringapp.ligretto.feature.game.ui.start

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.flaringapp.ligretto.core.arch.AndroidBackHandler
import com.flaringapp.ligretto.core.arch.ConsumeEffects
import com.flaringapp.ligretto.feature.game.ui.start.screen.GameStartScreenContent
import com.hoc081098.kmp.viewmodel.koin.compose.koinKmpViewModel
import org.koin.core.parameter.parametersOf

@Composable
internal fun GameStartScreen(
    restartLastGame: Boolean,
    openScore: () -> Unit,
    openClose: () -> Unit,
    store: GameStartViewModel = koinKmpViewModel { parametersOf(restartLastGame) },
) {
    val state by store.observeState().collectAsStateWithLifecycle()

    ConsumeEffects(store.observeEffect()) { effect ->
        when (effect) {
            GameStartEffect.StartGame -> openScore()
        }
    }

    AndroidBackHandler(true) {
        openClose()
    }

    GameStartScreenContent(
        state = state,
        dispatch = store::dispatch,
    )
}
