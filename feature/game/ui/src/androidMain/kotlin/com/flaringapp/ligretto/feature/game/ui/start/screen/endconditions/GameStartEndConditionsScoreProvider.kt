package com.flaringapp.ligretto.feature.game.ui.start.screen.endconditions

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.flaringapp.ligretto.feature.game.ui.start.GameStartState.EndConditions.ScoreLimit

internal class GameStartEndConditionsScoreProvider : PreviewParameterProvider<ScoreLimit> {

    companion object {

        fun disabled() = ScoreLimit()

        fun enabled() = disabled().copy(isEnabled = true)

        fun customSelected() = enabled().copy(
            selectedScore = 123,
            lastCustomScore = 123,
        )
    }

    override val values: Sequence<ScoreLimit> = sequenceOf(
        disabled(),
        enabled(),
        customSelected(),
    )
}
