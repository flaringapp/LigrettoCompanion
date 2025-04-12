package com.flaringapp.ligretto.feature.game.ui.common.endconditions.ui.options

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.flaringapp.ligretto.core.designsystem.AppTheme
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.GameEndConditionScoreLimitState
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.GameEndConditionTimeLimitState
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.GameEndConditionsScoreLimitCustomValueStateProvider
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.GameEndConditionsTimeLimitCustomValueStateProvider
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.ui.GameEndConditionsScope
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.ui.custom.CustomScoreInputDialog
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.ui.custom.CustomTimeInputDialog

@Preview
@Composable
private fun PreviewScore(
    @PreviewParameter(GameEndConditionsScoreLimitCustomValueStateProvider::class)
    state: GameEndConditionScoreLimitState.CustomInput,
) {
    AppTheme {
        GameEndConditionsScope.CustomScoreInputDialog(
            state = state,
            dispatch = {},
        )
    }
}

@Preview
@Composable
private fun PreviewTime(
    @PreviewParameter(GameEndConditionsTimeLimitCustomValueStateProvider::class)
    state: GameEndConditionTimeLimitState.CustomInput,
) {
    AppTheme {
        GameEndConditionsScope.CustomTimeInputDialog(
            state = state,
            dispatch = {},
        )
    }
}
