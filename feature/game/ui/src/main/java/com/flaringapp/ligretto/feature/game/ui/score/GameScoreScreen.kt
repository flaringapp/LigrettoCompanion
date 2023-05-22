package com.flaringapp.ligretto.feature.game.ui.score

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.flaringapp.ligretto.feature.game.ui.score.screen.GameScoreScreenContent
import com.flaringapp.ligretto.core.arch.ConsumeEffects
import com.flaringapp.ligretto.core.navigation.ScreenDestinationWithoutArguments
import org.koin.androidx.compose.getViewModel

internal object GameScoreDestination : ScreenDestinationWithoutArguments() {

    override val screenId: String = "game/score"
}

@Composable
internal fun GameScoreScreen(
    openNextLap: () -> Unit,
    openClose: () -> Unit,
    openEnd: () -> Unit,
    store: GameScoreViewModel = getViewModel(),
) {
    val state by store.observeState().collectAsStateWithLifecycle()

    ConsumeEffects(store.observeEffect()) { effect ->
        when (effect) {
            GameScoreEffect.OpenNextLap -> openNextLap()
            GameScoreEffect.EndGame -> openEnd()
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
