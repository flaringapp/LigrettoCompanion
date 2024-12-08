package com.flaringapp.ligretto.feature.game.ui.settings

import com.flaringapp.ligretto.core.arch.UiIntent
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.GameEndConditionScoreIntent
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.GameEndConditionTimeIntent

internal sealed interface GameSettingsIntent : UiIntent {

    sealed interface EndConditions : GameSettingsIntent {

        data class Score(
            val intent: GameEndConditionScoreIntent,
        ) : EndConditions

        data class Time(
            val intent: GameEndConditionTimeIntent,
        ) : EndConditions
    }

    data object Save : GameSettingsIntent
}
