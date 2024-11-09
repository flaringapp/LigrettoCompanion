package com.flaringapp.ligretto.feature.game.ui.start.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.flaringapp.ligretto.core.designsystem.AppTheme
import com.flaringapp.ligretto.feature.game.ui.start.GameStartState

@Preview
@Composable
private fun Preview(
    @PreviewParameter(GameStartStateProvider::class) state: GameStartState,
) {
    AppTheme {
        GameStartScreenContent(
            state = state,
            dispatch = {},
            close = {},
        )
    }
}
