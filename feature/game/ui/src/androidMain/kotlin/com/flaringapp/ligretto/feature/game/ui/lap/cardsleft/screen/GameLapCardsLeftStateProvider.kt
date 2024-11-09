package com.flaringapp.ligretto.feature.game.ui.lap.cardsleft.screen

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.flaringapp.ligretto.core.ui.ext.uiListOf
import com.flaringapp.ligretto.feature.game.ui.lap.cardsleft.GameLapCardsLeftState
import com.flaringapp.ligretto.feature.game.ui.lap.common.player.GameLapPlayerCardsStateProvider

internal class GameLapCardsLeftStateProvider : PreviewParameterProvider<GameLapCardsLeftState> {

    companion object {

        fun fewPlayers() = GameLapCardsLeftState(
            roundNumber = 2,
            playerCards = uiListOf(
                GameLapPlayerCardsStateProvider.zeroCards(1),
                GameLapPlayerCardsStateProvider.negativeCards(2),
                GameLapPlayerCardsStateProvider.positiveCards(3),
            ),
        )

        fun morePlayers() = GameLapCardsLeftState(
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
    }

    override val values: Sequence<GameLapCardsLeftState> = sequenceOf(
        fewPlayers(),
        morePlayers(),
    )
}
