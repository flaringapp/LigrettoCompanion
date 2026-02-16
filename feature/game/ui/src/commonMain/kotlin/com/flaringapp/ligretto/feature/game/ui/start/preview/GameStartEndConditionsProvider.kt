package com.flaringapp.ligretto.feature.game.ui.start.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.GameEndConditionScoreLimitState
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.GameEndConditionTimeLimitState
import com.flaringapp.ligretto.feature.game.ui.start.GameStartState

internal class GameStartEndConditionsProvider : PreviewParameterProvider<GameStartState.EndConditions> {

    companion object {

        fun conditions() = GameStartState.EndConditions(
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

    override val values: Sequence<GameStartState.EndConditions> = sequenceOf(
        conditions(),
        settingsExpanded(),
        settingsCollapsed(),
    )
}
