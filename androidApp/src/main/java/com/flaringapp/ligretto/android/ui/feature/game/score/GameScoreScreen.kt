package com.flaringapp.ligretto.android.ui.feature.game.score

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.flaringapp.ligretto.android.ui.feature.game.score.screen.GameScoreScreenContent
import com.flaringapp.ligretto.android.ui.mvi.ConsumeEffects
import com.flaringapp.ligretto.android.ui.utils.navigation.ScreenDestinationWithoutArguments
import org.koin.androidx.compose.getViewModel

object GameScoreDestination : ScreenDestinationWithoutArguments() {

    override val screenId: String = "game/score"
}

@Composable
fun GameScoreScreen(
    openNextLap: () -> Unit,
    openClose: () -> Unit,
    store: GameScoreViewModel = getViewModel(),
) {
    val state by store.observeState().collectAsStateWithLifecycle()

    ConsumeEffects(store.observeEffect()) { effect ->
        when (effect) {
            GameScoreEffect.OpenNextLap -> openNextLap()
        }
    }

    BackHandler(true) {
        openClose()
    }

    GameScoreScreenContent(
        state = state,
        dispatch = store::dispatch,
    )
}
