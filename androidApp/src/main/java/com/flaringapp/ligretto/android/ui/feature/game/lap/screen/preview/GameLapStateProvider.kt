package com.flaringapp.ligretto.android.ui.feature.game.lap.screen.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.flaringapp.ligretto.android.ui.feature.game.lap.GameLapState
import com.flaringapp.ligretto.model.Player

class GameLapStateProvider : PreviewParameterProvider<GameLapState> {

    companion object {
        fun empty() = GameLapState()

        fun filled() = GameLapState(
            playersCards = listOf(
                GameLapState.PlayerCards(
                    player = Player(1, "Andreo"),
                    cardsLeft = 2,
                    cardsOnTable = 14,
                ),
                GameLapState.PlayerCards(
                    player = Player(2, "Olenkka"),
                    cardsLeft = 0,
                    cardsOnTable = 20,
                ),
                GameLapState.PlayerCards(
                    player = Player(3, "Mario"),
                    cardsLeft = 3,
                    cardsOnTable = 10,
                ),
                GameLapState.PlayerCards(
                    player = Player(4, "Alina"),
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
