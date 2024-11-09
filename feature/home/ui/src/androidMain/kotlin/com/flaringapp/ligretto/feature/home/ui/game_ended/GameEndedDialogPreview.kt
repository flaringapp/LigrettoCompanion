package com.flaringapp.ligretto.feature.home.ui.game_ended

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.flaringapp.ligretto.core.designsystem.AppTheme

@Preview
@Composable
private fun Preview() {
    AppTheme {
        GameEndedDialog(
            dismiss = {},
        )
    }
}
