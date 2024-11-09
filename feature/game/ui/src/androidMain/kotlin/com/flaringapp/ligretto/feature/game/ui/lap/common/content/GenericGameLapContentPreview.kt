package com.flaringapp.ligretto.feature.game.ui.lap.common.content

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.flaringapp.ligretto.core.designsystem.AppTheme
import com.flaringapp.ligretto.core.ui.ext.uiListOf
import com.flaringapp.ligretto.feature.game.ui.lap.common.player.GameLapPlayerCardsStateProvider

@Preview
@Composable
private fun Preview() {
    AppTheme {
        GenericGameLapContent(
            roundNumber = 1,
            roundPhaseNumber = 1,
            topBarTitle = "Cards on table",
            cardScoreDelta = -2,
            playerCards = uiListOf(
                GameLapPlayerCardsStateProvider.zeroCards(1),
                GameLapPlayerCardsStateProvider.negativeCards(2),
                GameLapPlayerCardsStateProvider.positiveCards(3),
                GameLapPlayerCardsStateProvider.positiveCards(4),
                GameLapPlayerCardsStateProvider.zeroCards(5),
                GameLapPlayerCardsStateProvider.negativeCards(6),
                GameLapPlayerCardsStateProvider.negativeCards(7),
                GameLapPlayerCardsStateProvider.positiveCards(8),
            ),
            footerButtonText = "Next",
            playerCardDecrement = {},
            playerCardIncrement = {},
            onFooterButtonClick = {},
            onBackClick = {},
        )
    }
}
