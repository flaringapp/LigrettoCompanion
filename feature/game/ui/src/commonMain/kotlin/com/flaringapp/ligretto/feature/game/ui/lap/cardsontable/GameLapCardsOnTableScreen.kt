package com.flaringapp.ligretto.feature.game.ui.lap.cardsontable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.flaringapp.ligretto.core.arch.ConsumeEffects
import com.flaringapp.ligretto.feature.game.ui.lap.cardsontable.screen.GameLapCardsOnTableScreenContent
import org.koin.compose.viewmodel.koinViewModel
import kotlinx.serialization.Serializable

@Serializable
internal data object GameLapCardsOnTableDestination

@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun GameLapCardsOnTableScreen(
    navigateBack: () -> Unit,
    openScores: () -> Unit,
    openEnd: () -> Unit,
    openSettings: () -> Unit,
    openClose: () -> Unit,
    store: GameLapCardsOnTableViewModel = koinViewModel(),
) {
    val state by store.observeState().collectAsStateWithLifecycle()

    ConsumeEffects(store.observeEffect()) { effect ->
        when (effect) {
            GameLapCardsOnTableEffect.OpenScores -> openScores()
            GameLapCardsOnTableEffect.EndGame -> openEnd()
        }
    }

    GameLapCardsOnTableScreenContent(
        state = state,
        dispatch = store::dispatch,
        onBackClick = navigateBack,
        onChangeSettings = openSettings,
        onFinish = openClose,
    )
}
