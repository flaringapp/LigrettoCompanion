package com.flaringapp.ligretto.android.ui.feature.game.lap

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.flaringapp.ligretto.android.ui.feature.game.lap.screen.GameLapScreenContent
import com.flaringapp.ligretto.android.ui.mvi.ConsumeEffects
import com.flaringapp.ligretto.android.ui.utils.navigation.ScreenDestinationWithoutArguments
import org.koin.androidx.compose.getViewModel

object GameLapDestination : ScreenDestinationWithoutArguments() {

    override val screenId: String = "game/lap"
}

@Composable
fun GameLapScreen(
    openScores: () -> Unit,
    store: GameLapViewModel = getViewModel(),
) {
    val state by store.observeState().collectAsState()

    ConsumeEffects(store.observeEffect()) { effect ->
        when (effect) {
            GameLapEffect.OpenScores -> openScores()
        }
    }

    GameLapScreenContent(
        state = state,
        dispatch = store::dispatch,
    )
}
