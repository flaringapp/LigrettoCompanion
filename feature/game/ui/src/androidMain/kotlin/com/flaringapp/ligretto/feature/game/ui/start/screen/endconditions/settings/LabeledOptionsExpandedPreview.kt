package com.flaringapp.ligretto.feature.game.ui.start.screen.endconditions.settings

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.flaringapp.ligretto.core.designsystem.AppTheme
import com.flaringapp.ligretto.feature.game.ui.start.screen.endconditions.GameStartEndConditionsScope
import com.flaringapp.ligretto.feature.game.ui.start.screen.endconditions.GameStartEndConditionsScoreProvider

@Preview(showBackground = true)
@Composable
private fun Preview() = with(GameStartEndConditionsScope) {
    AppTheme {
        LabeledOptionsExpanded(
            title = "Set Score",
            message = "Select the target score to win the game",
        ) {
            ScoreOptions(
                state = GameStartEndConditionsScoreProvider.enabled(),
                dispatch = {},
            )
        }
    }
}
