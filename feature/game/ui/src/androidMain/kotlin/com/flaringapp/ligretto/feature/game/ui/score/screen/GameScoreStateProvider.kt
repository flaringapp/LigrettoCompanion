package com.flaringapp.ligretto.feature.game.ui.score.screen

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.flaringapp.ligretto.core.ui.ext.uiListOf
import com.flaringapp.ligretto.feature.game.ui.score.GameScoreState

internal class GameScoreStateProvider : PreviewParameterProvider<GameScoreState> {

    override val values: Sequence<GameScoreState> = sequenceOf(
        GameScoreState(
            nextRoundNumber = 2,
            playerScores = uiListOf(
                GameScorePlayerScoreProvider.firstPlace(),
                GameScorePlayerScoreProvider.secondPlace(),
                GameScorePlayerScoreProvider.thirdPlace(),
            ),
        ),
        GameScoreState(
            nextRoundNumber = 3,
            playerScores = uiListOf(
                GameScorePlayerScoreProvider.firstPlace(),
                GameScorePlayerScoreProvider.secondPlace(),
                GameScorePlayerScoreProvider.thirdPlace(),
                GameScorePlayerScoreProvider.forthPlace(),
                GameScorePlayerScoreProvider.forthPlace(),
                GameScorePlayerScoreProvider.forthPlace().copy(place = 5),
                GameScorePlayerScoreProvider.forthPlace().copy(place = 6),
                GameScorePlayerScoreProvider.forthPlace().copy(place = 7),
                GameScorePlayerScoreProvider.forthPlace().copy(place = 8),
                GameScorePlayerScoreProvider.forthPlace().copy(place = 9),
            ),
            endConditions = GameScoreEndConditionsProvider.scoreAndTime(),
        ),
    )
}
