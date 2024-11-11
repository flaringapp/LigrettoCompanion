package com.flaringapp.ligretto.feature.game.ui.lap.cardsleft.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.flaringapp.ligretto.feature.game.ui.lap.cardsleft.GameLapCardsLeftIntent
import com.flaringapp.ligretto.feature.game.ui.lap.cardsleft.GameLapCardsLeftState
import com.flaringapp.ligretto.feature.game.ui.lap.common.content.GenericGameLapContent
import ligretto_companion.feature.game.ui.generated.resources.Res
import ligretto_companion.feature.game.ui.generated.resources.lap_cards_left_next
import ligretto_companion.feature.game.ui.generated.resources.lap_cards_left_title
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun GameLapCardsLeftScreenContent(
    state: GameLapCardsLeftState,
    dispatch: (GameLapCardsLeftIntent) -> Unit,
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
        footerButtonText = stringResource(Res.string.lap_cards_left_next),
        playerCardIncrement = { dispatch(GameLapCardsLeftIntent.IncrementCards(it)) },
        playerCardDecrement = { dispatch(GameLapCardsLeftIntent.DecrementCards(it)) },
        onFooterButtonClick = { dispatch(GameLapCardsLeftIntent.OpenCardsOnTable) },
        onFinishGameClick = onFinish,
    )
}
