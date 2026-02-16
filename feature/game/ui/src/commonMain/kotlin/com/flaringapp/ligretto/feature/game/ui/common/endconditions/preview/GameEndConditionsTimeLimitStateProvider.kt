package com.flaringapp.ligretto.feature.game.ui.common.endconditions.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.GameEndConditionTimeLimitState

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
