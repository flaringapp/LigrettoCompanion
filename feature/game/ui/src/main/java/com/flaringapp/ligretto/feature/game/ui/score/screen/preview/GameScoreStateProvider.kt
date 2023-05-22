package com.flaringapp.ligretto.feature.game.ui.score.screen.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.flaringapp.ligretto.feature.game.ui.score.GameScoreState
import com.flaringapp.ligretto.feature.game.ui.score.GameScoreState.PlayerScore

internal class GameScoreStateProvider : PreviewParameterProvider<GameScoreState> {

    override val values: Sequence<GameScoreState> = sequenceOf(
        GameScoreState(
            scores = listOf(
                PlayerScore(1, "Andreo", 51),
                PlayerScore(2, "Mario", 24),
                PlayerScore(3, "Olenkka", 75),
            ),
        ),
        GameScoreState(
            scores = listOf(
                PlayerScore(1, "Brad", 12),
                PlayerScore(2, "Lucio", 23),
                PlayerScore(3, "Helen", 72),
                PlayerScore(4, "Kate", -14),
                PlayerScore(5, "Tony", 51),
                PlayerScore(6, "Tom", -21),
                PlayerScore(7, "Mark", 44),
                PlayerScore(8, "Dan", 0),
                PlayerScore(9, "May", 8),
                PlayerScore(10, "Jordan", 80),
            ),
            endConditions = GameScoreEndConditionsProvider.scoreAndTime(),
        )
    )
}
