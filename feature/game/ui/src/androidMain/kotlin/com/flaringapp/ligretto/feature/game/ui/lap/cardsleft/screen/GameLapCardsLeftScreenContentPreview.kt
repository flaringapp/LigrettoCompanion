package com.flaringapp.ligretto.feature.game.ui.lap.cardsleft.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.flaringapp.ligretto.core.designsystem.AppTheme
import com.flaringapp.ligretto.feature.game.ui.lap.cardsleft.GameLapCardsLeftState

@Preview
@Composable
private fun Preview(
    @PreviewParameter(GameLapCardsLeftStateProvider::class)
    state: GameLapCardsLeftState,
) {
    AppTheme {
        GameLapCardsLeftScreenContent(
            state = state,
            dispatch = {},
            onFinish = {},
        )
    }
}
