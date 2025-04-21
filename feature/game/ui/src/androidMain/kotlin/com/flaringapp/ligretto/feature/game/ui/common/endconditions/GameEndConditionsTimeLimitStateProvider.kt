package com.flaringapp.ligretto.feature.game.ui.common.endconditions

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

internal class GameEndConditionsTimeLimitStateProvider :
    PreviewParameterProvider<GameEndConditionTimeLimitState> {

    companion object {

        fun disabled() = GameEndConditionTimeLimitState()

        fun enabled() = disabled().copy(isEnabled = true)

        fun customSelected() = enabled().copy(
            selectedMinutes = 123,
            lastCustomMinutes = 123,
        )
    }

    override val values: Sequence<GameEndConditionTimeLimitState> = sequenceOf(
        disabled(),
        enabled(),
        customSelected(),
    )
}
