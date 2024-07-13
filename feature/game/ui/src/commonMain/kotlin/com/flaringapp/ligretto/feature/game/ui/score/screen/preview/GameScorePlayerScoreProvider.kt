package com.flaringapp.ligretto.feature.game.ui.score.screen.preview

import com.flaringapp.ligretto.feature.game.ui.score.GameScoreState.PlayerScore
import org.jetbrains.compose.ui.tooling.preview.PreviewParameterProvider

internal class GameScorePlayerScoreProvider : PreviewParameterProvider<PlayerScore> {

    companion object {

        fun firstPlace() = PlayerScore(
            place = 1,
            playerName = "Olenkka",
            score = 20,
        )

        fun secondPlace() = PlayerScore(
            place = 2,
            playerName = "Katyushka",
            score = 19,
        )

        fun thirdPlace() = PlayerScore(
            place = 3,
            playerName = "Andrew",
            score = 16,
        )

        fun forthPlace() = PlayerScore(
            place = 4,
            playerName = "Maryanko",
            score = -3,
        )
    }

    override val values: Sequence<PlayerScore> = sequenceOf(
        firstPlace(),
        secondPlace(),
        thirdPlace(),
        forthPlace(),
    )
}
