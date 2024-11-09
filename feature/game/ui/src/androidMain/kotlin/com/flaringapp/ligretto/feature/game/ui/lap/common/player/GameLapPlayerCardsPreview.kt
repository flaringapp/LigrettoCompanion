package com.flaringapp.ligretto.feature.game.ui.lap.common.player

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.flaringapp.ligretto.core.designsystem.AppTheme

@Preview
@Composable
private fun Preview(
    @PreviewParameter(GameLapPlayerCardsStateProvider::class)
    state: GameLapPlayerCardsState,
) {
    AppTheme {
        GameLapPlayerCards(
            state = state,
            increment = {},
            decrement = {},
        )
    }
}
