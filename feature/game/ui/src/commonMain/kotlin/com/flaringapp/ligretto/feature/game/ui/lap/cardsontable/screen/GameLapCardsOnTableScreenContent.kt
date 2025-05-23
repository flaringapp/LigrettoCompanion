package com.flaringapp.ligretto.feature.game.ui.lap.cardsontable.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.flaringapp.ligretto.feature.game.ui.lap.cardsontable.GameLapCardsOnTableIntent
import com.flaringapp.ligretto.feature.game.ui.lap.cardsontable.GameLapCardsOnTableState
import com.flaringapp.ligretto.feature.game.ui.lap.common.content.GenericGameLapContent
import com.flaringapp.ligretto.feature.game.ui.lap.end.GameLapEndLapDialog
import ligretto_companion.feature.game.ui.generated.resources.Res
import ligretto_companion.feature.game.ui.generated.resources.lap_cards_on_table_finish_round
import ligretto_companion.feature.game.ui.generated.resources.lap_cards_on_table_title
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun GameLapCardsOnTableScreenContent(
    state: GameLapCardsOnTableState,
    dispatch: (GameLapCardsOnTableIntent) -> Unit,
    onBackClick: () -> Unit,
    onChangeSettings: () -> Unit,
    onFinish: () -> Unit,
    modifier: Modifier = Modifier,
) {
    GenericGameLapContent(
        modifier = modifier,
        roundNumber = state.roundNumber,
        roundPhaseNumber = 2,
        cardScoreDelta = 1,
        topBarTitle = stringResource(Res.string.lap_cards_on_table_title),
        playerCards = state.playerCards,
        footerButtonText = stringResource(Res.string.lap_cards_on_table_finish_round),
        playerCardIncrement = { dispatch(GameLapCardsOnTableIntent.IncrementCards(it)) },
        playerCardDecrement = { dispatch(GameLapCardsOnTableIntent.DecrementCards(it)) },
        onFooterButtonClick = { dispatch(GameLapCardsOnTableIntent.EndLap) },
        onBackClick = onBackClick,
        onChangeSettingsClick = onChangeSettings,
        onFinishGameClick = onFinish,
    )

    if (state.showConfirmEndLap) {
        GameLapEndLapDialog(
            onConfirm = { dispatch(GameLapCardsOnTableIntent.EndLapConfirmed) },
            onDismiss = { dispatch(GameLapCardsOnTableIntent.HideEndLapConfirmation) },
        )
    }
}
