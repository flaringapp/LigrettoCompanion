package com.flaringapp.ligretto.feature.game.ui.score.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.flaringapp.ligretto.core.designsystem.AppTheme
import com.flaringapp.ligretto.feature.game.ui.score.GameScoreState

@Preview(showBackground = true)
@Composable
private fun Preview(
    @PreviewParameter(GameScoreEndConditionsProvider::class)
    state: GameScoreState.EndConditions,
) {
    AppTheme {
        GameScoreEndConditionContent(state)
    }
}
