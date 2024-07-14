package com.flaringapp.ligretto.feature.game.ui.lap.cardsontable.preview

import com.flaringapp.ligretto.core.ui.ext.uiListOf
import com.flaringapp.ligretto.feature.game.ui.lap.cardsontable.GameLapCardsOnTableState
import com.flaringapp.ligretto.feature.game.ui.lap.common.player.GameLapPlayerCardsStateProvider
import org.jetbrains.compose.ui.tooling.preview.PreviewParameterProvider

internal class GameLapCardsOnTableStateProvider :
    PreviewParameterProvider<GameLapCardsOnTableState> {

    companion object {

        fun fewPlayers() = GameLapCardsOnTableState(
            roundNumber = 2,
            playerCards = uiListOf(
                GameLapPlayerCardsStateProvider.zeroCards(1),
                GameLapPlayerCardsStateProvider.negativeCards(2),
                GameLapPlayerCardsStateProvider.positiveCards(3),
            ),
        )

        fun morePlayers() = GameLapCardsOnTableState(
            roundNumber = 3,
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
        )

        fun endLapConfirmation() = morePlayers().copy(
            showConfirmEndLap = true,
        )
    }

    override val values: Sequence<GameLapCardsOnTableState> = sequenceOf(
        fewPlayers(),
        morePlayers(),
        endLapConfirmation(),
    )
}
