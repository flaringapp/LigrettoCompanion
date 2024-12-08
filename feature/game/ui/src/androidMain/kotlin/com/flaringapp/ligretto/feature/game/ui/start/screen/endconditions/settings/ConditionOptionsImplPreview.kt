package com.flaringapp.ligretto.feature.game.ui.start.screen.endconditions.settings

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.flaringapp.ligretto.core.designsystem.AppTheme
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.GameEndConditionScoreLimitState
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.GameEndConditionTimeLimitState
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.GameEndConditionsScoreLimitStateProvider
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.GameEndConditionsTimeLimitStateProvider
import com.flaringapp.ligretto.feature.game.ui.start.screen.endconditions.GameStartEndConditionsScope

@Preview(showBackground = true)
@Composable
private fun PreviewScore(
    @PreviewParameter(GameEndConditionsScoreLimitStateProvider::class)
    state: GameEndConditionScoreLimitState,
) {
    AppTheme {
        GameStartEndConditionsScope.ScoreOptions(
            state = state,
            dispatch = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewTime(
    @PreviewParameter(GameEndConditionsTimeLimitStateProvider::class)
    state: GameEndConditionTimeLimitState,
) {
    AppTheme {
        GameStartEndConditionsScope.TimeOptions(
            state = state,
            dispatch = {},
        )
    }
}
