package com.flaringapp.ligretto.android.ui.feature.game.lap

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.flaringapp.ligretto.android.ui.feature.game.lap.screen.GameLapScreenContent
import com.flaringapp.ligretto.android.ui.utils.navigation.ScreenDestinationWithoutArguments
import com.flaringapp.ligretto.core.arch.ConsumeEffects
import org.koin.androidx.compose.getViewModel

object GameLapDestination : ScreenDestinationWithoutArguments() {

    override val screenId: String = "game/lap"
}

@Composable
fun GameLapScreen(
    openScores: () -> Unit,
    openClose: () -> Unit,
    openEnd: () -> Unit,
    store: GameLapViewModel = getViewModel(),
) {
    val state by store.observeState().collectAsStateWithLifecycle()

    ConsumeEffects(store.observeEffect()) { effect ->
        when (effect) {
            GameLapEffect.OpenScores -> openScores()
            GameLapEffect.EndGame -> openEnd()
        }
    }

    BackHandler(true) {
        openClose()
    }

    GameLapScreenContent(
        state = state,
        dispatch = store::dispatch,
    )
}
