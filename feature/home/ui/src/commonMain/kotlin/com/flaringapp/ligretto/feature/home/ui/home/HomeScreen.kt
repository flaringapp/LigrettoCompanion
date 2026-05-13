package com.flaringapp.ligretto.feature.home.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.flaringapp.ligretto.core.arch.ConsumeEffects
import com.flaringapp.ligretto.feature.home.ui.home.screen.HomeScreenContent
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun HomeScreen(
    openStartGame: (restartLastGame: Boolean) -> Unit,
    openResumeGame: (openLap: Boolean) -> Unit,
    openActiveGameEnded: () -> Unit,
    store: HomeViewModel = koinViewModel(),
) {
    val state by store.observeState().collectAsStateWithLifecycle()

    ConsumeEffects(store.observeEffect()) { effect ->
        when (effect) {
            is HomeEffect.OpenStartGame -> openStartGame(effect.restartLastGame)
            is HomeEffect.OpenResumeGame -> openResumeGame(effect.openLap)
            is HomeEffect.OpenActiveGameEnded -> openActiveGameEnded()
        }
    }

    HomeScreenContent(
        state = state,
        dispatch = store::dispatch,
    )
}
