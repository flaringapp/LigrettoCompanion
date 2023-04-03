package com.flaringapp.ligretto.android.ui.feature.game.end

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.flaringapp.ligretto.android.ui.feature.game.end.screen.GameEndScreenContent
import com.flaringapp.ligretto.android.ui.mvi.ConsumeEffects
import com.flaringapp.ligretto.android.ui.utils.navigation.ScreenDestinationWithoutArguments
import org.koin.androidx.compose.getViewModel

object GameEndDestination : ScreenDestinationWithoutArguments() {

    override val screenId: String = "game/end"
}

@Composable
fun GameEndScreen(
    closeGame: () -> Unit,
    store: GameEndViewModel = getViewModel(),
) {
    val state by store.observeState().collectAsState()

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
