package com.flaringapp.ligretto.feature.game.ui.settings.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.flaringapp.ligretto.core.designsystem.AppTheme
import com.flaringapp.ligretto.feature.game.ui.settings.GameSettingsState

@Preview(showBackground = true)
@Composable
private fun Preview(
    @PreviewParameter(GameSettingsStateProvider::class)
    state: GameSettingsState,
) {
    AppTheme {
        GameSettingsDialogContent(
            state = state,
            dispatch = {},
            close = {},
        )
    }
}
