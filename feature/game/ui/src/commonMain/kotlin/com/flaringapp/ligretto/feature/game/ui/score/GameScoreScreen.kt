package com.flaringapp.ligretto.feature.game.ui.score

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.backhandler.BackHandler
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.flaringapp.ligretto.core.arch.ConsumeEffects
import com.flaringapp.ligretto.feature.game.ui.score.screen.GameScoreScreenContent
import org.koin.compose.viewmodel.koinViewModel
import kotlinx.serialization.Serializable

@Serializable
internal data object GameScoreDestination

@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun GameScoreScreen(
    openNextLap: () -> Unit,
    openSettings: () -> Unit,
    openClose: () -> Unit,
    openEnd: () -> Unit,
    store: GameScoreViewModel = koinViewModel(),
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
        onChangeSettingsClick = openSettings,
        onFinishGameClick = openClose,
    )
}
