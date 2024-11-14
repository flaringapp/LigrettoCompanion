package com.flaringapp.ligretto.feature.game.ui.start.screen.endconditions.settings

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.flaringapp.ligretto.core.designsystem.AppTheme
import com.flaringapp.ligretto.feature.game.ui.start.GameStartState.EndConditions.ScoreLimit
import com.flaringapp.ligretto.feature.game.ui.start.GameStartState.EndConditions.TimeLimit
import com.flaringapp.ligretto.feature.game.ui.start.screen.endconditions.GameStartEndConditionsScope
import com.flaringapp.ligretto.feature.game.ui.start.screen.endconditions.GameStartEndConditionsScoreProvider
import com.flaringapp.ligretto.feature.game.ui.start.screen.endconditions.GameStartEndConditionsTimeProvider

@Preview(showBackground = true)
@Composable
private fun PreviewScore(
    @PreviewParameter(GameStartEndConditionsScoreProvider::class)
    state: ScoreLimit,
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
    @PreviewParameter(GameStartEndConditionsTimeProvider::class)
    state: TimeLimit,
) {
    AppTheme {
        GameStartEndConditionsScope.TimeOptions(
            state = state,
            dispatch = {},
        )
    }
}
