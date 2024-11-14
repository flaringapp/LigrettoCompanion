package com.flaringapp.ligretto.feature.game.ui.start.screen.endconditions

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.flaringapp.ligretto.feature.game.ui.start.GameStartState.EndConditions.TimeLimit

internal class GameStartEndConditionsTimeProvider : PreviewParameterProvider<TimeLimit> {

    companion object {

        fun disabled() = TimeLimit()

        fun enabled() = disabled().copy(isEnabled = true)

        fun customSelected() = enabled().copy(
            selectedMinutes = 123,
            lastCustomMinutes = 123,
        )
    }

    override val values: Sequence<TimeLimit> = sequenceOf(
        disabled(),
        enabled(),
        customSelected(),
    )
}
