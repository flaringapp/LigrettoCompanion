package com.flaringapp.ligretto.feature.home.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.flaringapp.ligretto.core.arch.ConsumeEffects
import com.flaringapp.ligretto.core.navigation.ScreenDestinationWithoutArguments
import com.flaringapp.ligretto.feature.home.ui.screen.HomeScreenContent
import org.koin.androidx.compose.getViewModel

internal object HomeScreenDestination : ScreenDestinationWithoutArguments() {

    override val screenId: String = "home/main"
}

@Composable
internal fun HomeScreen(
    openStartGame: (restartLastGame: Boolean) -> Unit,
    openResumeGame: (openLap: Boolean) -> Unit,
    store: HomeViewModel = getViewModel(),
) {
    val state by store.observeState().collectAsStateWithLifecycle()

    ConsumeEffects(store.observeEffect()) { effect ->
        when (effect) {
            is HomeEffect.OpenStartGame -> openStartGame(effect.restartLastGame)
            is HomeEffect.OpenResumeGame -> openResumeGame(effect.openLap)
        }
    }

    HomeScreenContent(
        state = state,
        dispatch = store::dispatch,
    )
}
