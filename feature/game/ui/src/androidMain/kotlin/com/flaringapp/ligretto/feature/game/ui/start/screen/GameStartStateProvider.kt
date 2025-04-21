package com.flaringapp.ligretto.feature.game.ui.start.screen

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.flaringapp.ligretto.feature.game.ui.start.GameStartState

internal class GameStartStateProvider : PreviewParameterProvider<GameStartState> {

    companion object {

        fun initial() = GameStartState()

        fun endConditionsExpandedConditionsSomethingSelected() = with(initial()) {
            copy(
                endConditions = endConditions.copy(
                    score = endConditions.score.copy(isEnabled = true),
                ),
            )
        }

        fun endConditionsExpandedOptionsSomethingSelected() = with(
            endConditionsExpandedConditionsSomethingSelected(),
        ) {
            copy(
                endConditions = endConditions.copy(
                    isExpandedConditionsCompleted = true,
                ),
            )
        }

        fun endConditionsExpandedOptionsEmpty() = GameStartState(
            endConditions = GameStartState.EndConditions(
                isExpandedConditionsCompleted = true,
            ),
        )

        fun playersEmpty() = GameStartState(
            players = GameStartPlayersProvider.empty(),
            endConditions = GameStartState.EndConditions(
                isExpandedConditionsCompleted = true,
                isExpandedOptionsCompleted = true,
            ),
        )

        fun playersShort() = with(playersEmpty()) {
            copy(
                players = GameStartPlayersProvider.short(),
                endConditions = endConditions.copy(
                    score = endConditions.score.copy(isEnabled = true),
                ),
            )
        }

        fun playersLong() = with(playersShort()) {
            copy(
                players = GameStartPlayersProvider.long(),
                endConditions = endConditions.copy(
                    time = endConditions.time.copy(isEnabled = true),
                ),
            )
        }
    }

    override val values: Sequence<GameStartState> = sequenceOf(
        initial(),
        endConditionsExpandedConditionsSomethingSelected(),
        endConditionsExpandedOptionsSomethingSelected(),
        endConditionsExpandedOptionsEmpty(),
        playersEmpty(),
        playersShort(),
        playersLong(),
    )
}
