package com.flaringapp.ligretto.feature.game.ui.start.screen.endconditions

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.flaringapp.ligretto.feature.game.ui.start.GameStartState.EndConditions

internal class GameStartEndConditionsProvider : PreviewParameterProvider<EndConditions> {

    companion object {

        fun conditions() = EndConditions(
            score = EndConditions.ScoreLimit(isEnabled = true),
            time = EndConditions.TimeLimit(isEnabled = true),
        )

        fun settingsExpanded() = conditions().copy(
            isExpandedConditionsCompleted = true,
        )

        fun settingsCollapsed() = settingsExpanded().copy(
            isExpandedOptionsCompleted = true,
        )
    }

    override val values: Sequence<EndConditions> = sequenceOf(
        conditions(),
        settingsExpanded(),
        settingsCollapsed(),
    )
}
