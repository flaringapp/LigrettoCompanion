package com.flaringapp.ligretto.feature.game.ui.score.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.flaringapp.ligretto.core.designsystem.AppTheme

@Preview(showBackground = true)
@Composable
private fun PreviewScore() {
    AppTheme {
        GameScoreEndConditionScore(
            score = 100,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewTime() {
    AppTheme {
        GameScoreEndConditionTime(
            minutes = 30,
        )
    }
}
