package com.flaringapp.ligretto.feature.game.ui.settings

import com.flaringapp.ligretto.core.arch.UiState
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.GameEndConditionScoreLimitState
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.GameEndConditionTimeLimitState

internal data class GameSettingsState(
    val score: GameEndConditionScoreLimitState = GameEndConditionScoreLimitState(),
    val time: GameEndConditionTimeLimitState = GameEndConditionTimeLimitState(),
) : UiState
