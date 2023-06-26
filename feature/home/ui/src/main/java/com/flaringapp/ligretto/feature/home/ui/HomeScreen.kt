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
    openStartGame: () -> Unit,
    store: HomeViewModel = getViewModel(),
) {
    val state by store.observeState().collectAsStateWithLifecycle()

    ConsumeEffects(store.observeEffect()) { effect ->
        when (effect) {
            // TODO navigation argument
            is HomeEffect.OpenStartGame -> openStartGame()
        }
    }

    HomeScreenContent(
        state = state,
        dispatch = store::dispatch,
    )
}
