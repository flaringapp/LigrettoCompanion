package com.flaringapp.ligretto.feature.game.ui.start

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.flaringapp.ligretto.core.arch.ConsumeEffects
import com.flaringapp.ligretto.core.navigation.ScreenDestinationWithoutArguments
import com.flaringapp.ligretto.feature.game.ui.start.screen.GameStartScreenContent
import org.koin.androidx.compose.getViewModel

internal object GameStartDestination : ScreenDestinationWithoutArguments() {

    override val screenId: String = "game/start"
}

@Composable
internal fun GameStartScreen(
    openScore: () -> Unit,
    openClose: () -> Unit,
    store: GameStartViewModel = getViewModel(),
) {
    val state by store.observeState().collectAsStateWithLifecycle()

    ConsumeEffects(store.observeEffect()) { effect ->
        when (effect) {
            GameStartEffect.StartGame -> openScore()
        }
    }

    BackHandler(true) {
        openClose()
    }

    GameStartScreenContent(
        state = state,
        dispatch = store::dispatch,
    )
}
