package com.flaringapp.ligretto.feature.game.ui.common.endconditions

import com.flaringapp.ligretto.core.arch.UiState
import com.flaringapp.ligretto.core.ui.ext.UiList
import com.flaringapp.ligretto.core.ui.ext.uiListOf

internal data class GameEndConditionScoreLimitState(
    val staticOptions: UiList<Int> = uiListOf(50, 100, 150),
    val isEnabled: Boolean = false,
    val selectedScore: Int = staticOptions.first(),
    val lastCustomScore: Int? = null,
) : UiState

internal data class GameEndConditionTimeLimitState(
    val staticOptions: UiList<Int> = uiListOf(15, 30, 60),
    val isEnabled: Boolean = false,
    val selectedMinutes: Int = staticOptions.first(),
    val lastCustomMinutes: Int? = null,
) : UiState
