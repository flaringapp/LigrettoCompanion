package com.flaringapp.ligretto.feature.game.ui.lap.end

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.flaringapp.ligretto.core.designsystem.AppTheme

@Preview
@Composable
private fun Preview() {
    AppTheme {
        GameLapEndLapDialog(
            onConfirm = {},
            onDismiss = {},
        )
    }
}
