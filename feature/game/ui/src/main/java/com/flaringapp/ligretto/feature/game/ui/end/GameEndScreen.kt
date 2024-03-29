package com.flaringapp.ligretto.feature.game.ui.end

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.flaringapp.ligretto.core.arch.ConsumeEffects
import com.flaringapp.ligretto.core.navigation.ScreenDestinationWithoutArguments
import com.flaringapp.ligretto.feature.game.ui.end.screen.GameEndScreenContent
import org.koin.androidx.compose.koinViewModel

internal object GameEndDestination : ScreenDestinationWithoutArguments() {

    override val screenId: String = "game/end"
}

@Composable
internal fun GameEndScreen(
    closeGame: () -> Unit,
    store: GameEndViewModel = koinViewModel(),
) {
    val state by store.observeState().collectAsStateWithLifecycle()

    ConsumeEffects(store.observeEffect()) { effect ->
        when (effect) {
            GameEndEffect.Close -> closeGame()
        }
    }

    BackHandler(true) {
        store.dispatch(GameEndIntent.Finish)
    }

    GameEndScreenContent(
        state = state,
        dispatch = store::dispatch,
    )
}
