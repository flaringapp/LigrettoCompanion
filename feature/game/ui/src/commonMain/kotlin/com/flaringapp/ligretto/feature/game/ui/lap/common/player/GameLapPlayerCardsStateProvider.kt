package com.flaringapp.ligretto.feature.game.ui.lap.common.player

import org.jetbrains.compose.ui.tooling.preview.PreviewParameterProvider

internal class GameLapPlayerCardsStateProvider : PreviewParameterProvider<GameLapPlayerCardsState> {

    companion object {

        fun zeroCards() = GameLapPlayerCardsState(
            playerName = "Andrew",
            totalScore = 0,
            cardsCount = 0,
        )

        fun positiveCards() = GameLapPlayerCardsState(
            playerName = "Olenkka",
            totalScore = 12,
            cardsCount = 3,
        )

        fun negativeCards() = GameLapPlayerCardsState(
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
