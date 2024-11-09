package com.flaringapp.ligretto.feature.game.ui.lap.common.player

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

internal class GameLapPlayerCardsStateProvider : PreviewParameterProvider<GameLapPlayerCardsState> {

    companion object {

        fun zeroCards(playerId: Long = 0) = GameLapPlayerCardsState(
            playerId = playerId,
            playerName = "Andrew",
            totalScore = 0,
            cardsCount = 0,
        )

        fun positiveCards(playerId: Long = 0) = GameLapPlayerCardsState(
            playerId = playerId,
            playerName = "Olenkka",
            totalScore = 12,
            cardsCount = 3,
        )

        fun negativeCards(playerId: Long = 0) = GameLapPlayerCardsState(
            playerId = playerId,
            playerName = "MariAnchor",
            totalScore = -14,
            cardsCount = -2,
        )
    }

    override val values: Sequence<GameLapPlayerCardsState> = sequenceOf(
        zeroCards(),
        positiveCards(),
        negativeCards(),
    )
}
