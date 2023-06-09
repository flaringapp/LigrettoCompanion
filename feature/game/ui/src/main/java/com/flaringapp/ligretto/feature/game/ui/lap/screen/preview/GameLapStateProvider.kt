package com.flaringapp.ligretto.feature.game.ui.lap.screen.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.flaringapp.ligretto.core.ui.ext.uiListOf
import com.flaringapp.ligretto.feature.game.ui.lap.GameLapState

internal class GameLapStateProvider : PreviewParameterProvider<GameLapState> {

    companion object {
        fun empty() = GameLapState()

        fun filled() = GameLapState(
            playersCards = uiListOf(
                GameLapState.PlayerCards(
                    player = GameLapState.Player(1, "Andreo"),
                    score = 10,
                    cardsLeft = 2,
                    cardsOnTable = 14,
                ),
                GameLapState.PlayerCards(
                    player = GameLapState.Player(2, "Olenkka"),
                    score = 20,
                    cardsLeft = 0,
                    cardsOnTable = 20,
                ),
                GameLapState.PlayerCards(
                    player = GameLapState.Player(3, "Mario"),
                    score = 61,
                    cardsLeft = 3,
                    cardsOnTable = 10,
                ),
                GameLapState.PlayerCards(
                    player = GameLapState.Player(4, "Alina"),
                    score = -12,
                    cardsLeft = 8,
                    cardsOnTable = 4,
                )
            ),
        )

        fun end() = filled().copy(
            showConfirmEndLap = true,
        )
    }

    override val values: Sequence<GameLapState> = sequenceOf(
        empty(),
        filled(),
        end(),
    )
}
