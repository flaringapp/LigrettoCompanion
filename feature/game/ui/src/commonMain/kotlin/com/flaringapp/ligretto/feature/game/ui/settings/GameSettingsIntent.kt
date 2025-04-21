package com.flaringapp.ligretto.feature.game.ui.settings

import com.flaringapp.ligretto.core.arch.UiIntent
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.GameEndConditionScoreIntent
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.GameEndConditionTimeIntent

internal sealed interface GameSettingsIntent : UiIntent {

    data object LoadData : GameSettingsIntent

    data class InitData(
        val state: GameSettingsState,
    ) : GameSettingsIntent

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
