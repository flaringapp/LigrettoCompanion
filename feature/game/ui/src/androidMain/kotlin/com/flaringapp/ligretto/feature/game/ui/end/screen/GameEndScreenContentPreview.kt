package com.flaringapp.ligretto.feature.game.ui.end.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.flaringapp.ligretto.core.designsystem.AppTheme
import com.flaringapp.ligretto.feature.game.ui.end.GameEndState

@PreviewLightDark
@Composable
private fun Preview(
    @PreviewParameter(GameEndStateProvider::class)
    state: GameEndState,
) {
    AppTheme {
        GameEndScreenContent(
            state = state,
            dispatch = {},
        )
    }
}
