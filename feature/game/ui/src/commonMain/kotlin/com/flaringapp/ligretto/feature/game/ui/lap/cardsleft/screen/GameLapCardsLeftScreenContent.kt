package com.flaringapp.ligretto.feature.game.ui.lap.cardsleft.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.flaringapp.ligretto.core.designsystem.AppTheme
import com.flaringapp.ligretto.feature.game.ui.lap.cardsleft.GameLapCardsLeftIntent
import com.flaringapp.ligretto.feature.game.ui.lap.cardsleft.GameLapCardsLeftState
import com.flaringapp.ligretto.feature.game.ui.lap.cardsleft.preview.GameLapCardsLeftStateProvider
import com.flaringapp.ligretto.feature.game.ui.lap.common.content.GenericGameLapContent
import ligretto_companion.feature.game.ui.generated.resources.Res
import ligretto_companion.feature.game.ui.generated.resources.lap_cards_left_next_hint
import ligretto_companion.feature.game.ui.generated.resources.lap_cards_left_next_label
import ligretto_companion.feature.game.ui.generated.resources.lap_cards_left_title
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun GameLapCardsLeftScreenContent(
    state: GameLapCardsLeftState,
    dispatch: (GameLapCardsLeftIntent) -> Unit,
    onChangeSettings: () -> Unit,
    onFinish: () -> Unit,
    modifier: Modifier = Modifier,
) {
    GenericGameLapContent(
        modifier = modifier,
        roundNumber = state.roundNumber,
        roundPhaseNumber = 1,
        cardScoreDelta = -2,
        topBarTitle = stringResource(Res.string.lap_cards_left_title),
        playerCards = state.playerCards,
        footerButtonHint = stringResource(Res.string.lap_cards_left_next_hint),
        footerButtonActionLabel = stringResource(Res.string.lap_cards_left_next_label),
        playerCardIncrement = { dispatch(GameLapCardsLeftIntent.IncrementCards(it)) },
        playerCardDecrement = { dispatch(GameLapCardsLeftIntent.DecrementCards(it)) },
        onFooterButtonClick = { dispatch(GameLapCardsLeftIntent.OpenCardsOnTable) },
        onChangeSettingsClick = onChangeSettings,
        onFinishGameClick = onFinish,
    )
}

@Preview
@Composable
private fun Preview(
    @PreviewParameter(GameLapCardsLeftStateProvider::class)
    state: GameLapCardsLeftState,
) {
    AppTheme {
        GameLapCardsLeftScreenContent(
            state = state,
            dispatch = {},
            onChangeSettings = {},
            onFinish = {},
        )
    }
}
