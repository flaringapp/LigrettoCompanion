package com.flaringapp.ligretto.feature.game.ui.lap.start.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.flaringapp.ligretto.core.designsystem.AppTheme
import com.flaringapp.ligretto.feature.game.ui.lap.start.GameLapStartState

@Preview
@Composable
private fun Preview(
    @PreviewParameter(GameLapStartStateProvider::class)
    state: GameLapStartState,
) {
    AppTheme {
        GameLapStartBackgroundNumber(
            lapNumber = state.lapNumber ?: return@AppTheme,
        )
    }
}
