package com.flaringapp.ligretto.feature.game.ui.close

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.flaringapp.ligretto.core.designsystem.AppTheme

@Preview
@Composable
private fun Preview() {
    AppTheme {
        GameCloseDialogContent(
            approve = {},
            dismiss = {},
        )
    }
}
