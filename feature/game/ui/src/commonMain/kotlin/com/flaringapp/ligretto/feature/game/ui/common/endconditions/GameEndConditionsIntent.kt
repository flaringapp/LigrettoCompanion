package com.flaringapp.ligretto.feature.game.ui.common.endconditions

import com.flaringapp.ligretto.core.arch.UiIntent

internal sealed interface GameEndConditionScoreIntent : UiIntent {

    data class SetEnabled(
        val isEnabled: Boolean,
    ) : GameEndConditionScoreIntent

    data class ValueChange(
        val score: Int,
    ) : GameEndConditionScoreIntent

    data object SelectCustomValue : GameEndConditionScoreIntent

    data class CustomValueSelected(
        val score: Int,
    ) : GameEndConditionScoreIntent
}

internal sealed interface GameEndConditionTimeIntent : UiIntent {

    data class SetEnabled(
        val isEnabled: Boolean,
    ) : GameEndConditionTimeIntent

    data class ValueChange(
        val minutes: Int,
    ) : GameEndConditionTimeIntent

    data object SelectCustomValue : GameEndConditionTimeIntent

    data class CustomValueSelected(
        val minutes: Int,
    ) : GameEndConditionTimeIntent
}
