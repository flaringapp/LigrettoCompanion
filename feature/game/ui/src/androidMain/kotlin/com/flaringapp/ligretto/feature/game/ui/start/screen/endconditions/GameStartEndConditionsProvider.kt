package com.flaringapp.ligretto.feature.game.ui.start.screen.endconditions

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.GameEndConditionScoreLimitState
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.GameEndConditionTimeLimitState
import com.flaringapp.ligretto.feature.game.ui.start.GameStartState.EndConditions

internal class GameStartEndConditionsProvider : PreviewParameterProvider<EndConditions> {

    companion object {

        fun conditions() = EndConditions(
            score = GameEndConditionScoreLimitState(isEnabled = true),
            time = GameEndConditionTimeLimitState(isEnabled = true),
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
