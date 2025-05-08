package com.flaringapp.ligretto.feature.game.ui.common.endconditions

import com.flaringapp.ligretto.core.arch.UiState
import com.flaringapp.ligretto.core.ui.ext.StringDescription
import com.flaringapp.ligretto.core.ui.ext.UiList
import com.flaringapp.ligretto.core.ui.ext.uiListOf

internal data class GameEndConditionScoreLimitState(
    val staticOptions: UiList<Int> = uiListOf(50, 100, 150),
    val isEnabled: Boolean = false,
    val selectedScore: Int = staticOptions.first(),
    val lastCustomScore: Int? = selectedScore.takeIf { !staticOptions.contains(it) },
    val customScoreInput: CustomInput? = null,
) : UiState {

    data class CustomInput(
        val value: String = "",
        val error: StringDescription? = null,
    )
}

internal data class GameEndConditionTimeLimitState(
    val staticOptions: UiList<Int> = uiListOf(15, 30, 60),
    val isEnabled: Boolean = false,
    val selectedMinutes: Int = staticOptions.first(),
    val lastCustomMinutes: Int? = selectedMinutes.takeIf { !staticOptions.contains(it) },
    val customMinutesInput: CustomInput? = null,
) : UiState {

    data class CustomInput(
        val value: String = "",
        val error: StringDescription? = null,
    )
}
