package com.flaringapp.ligretto.feature.game.ui.common.endconditions.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.GameEndConditionScoreLimitState

internal class GameEndConditionsScoreLimitStateProvider :
    PreviewParameterProvider<GameEndConditionScoreLimitState> {

    companion object {

        fun disabled() = GameEndConditionScoreLimitState()

        fun enabled() = disabled().copy(isEnabled = true)

        fun customSelected() = enabled().copy(
            selectedScore = 123,
            lastCustomScore = 123,
        )
    }

    override val values: Sequence<GameEndConditionScoreLimitState> = sequenceOf(
        disabled(),
        enabled(),
        customSelected(),
    )
}
