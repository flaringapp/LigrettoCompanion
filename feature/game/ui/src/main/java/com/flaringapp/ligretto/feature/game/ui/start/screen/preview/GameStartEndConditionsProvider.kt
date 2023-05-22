package com.flaringapp.ligretto.feature.game.ui.start.screen.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.flaringapp.ligretto.feature.game.ui.start.GameStartState.EndConditions

class GameStartEndConditionsProvider : PreviewParameterProvider<EndConditions> {

    companion object {
        fun none() = EndConditions(
            score = EndConditions.ScoreLimit(isEnabled = false),
            time = EndConditions.TimeLimit(isEnabled = false),
        )

        fun scoreOnly() = none().copy(
            score = EndConditions.ScoreLimit(
                isEnabled = true,
                value = "132",
            )
        )

        fun all() = scoreOnly().copy(
            time = EndConditions.TimeLimit(
                isEnabled = true,
                hours = "1",
                minutes = "30",
            )
        )
    }

    override val values: Sequence<EndConditions> = sequenceOf(
        none(),
        scoreOnly(),
        all(),
    )
}
