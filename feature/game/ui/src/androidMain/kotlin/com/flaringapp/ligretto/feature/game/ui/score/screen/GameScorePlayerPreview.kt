package com.flaringapp.ligretto.feature.game.ui.score.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.flaringapp.ligretto.core.designsystem.AppTheme
import com.flaringapp.ligretto.feature.game.ui.score.GameScoreState.PlayerScore

@Preview
@Composable
private fun Preview(
    @PreviewParameter(GameScorePlayerScoreProvider::class)
    state: PlayerScore,
) {
    AppTheme {
        GameScorePlayer(
            state = state,
        )
    }
}
