package com.flaringapp.ligretto.feature.game.ui.start.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.flaringapp.ligretto.core.designsystem.AppTheme

@Preview(showBackground = true)
@Composable
private fun PreviewEnabled() {
    AppTheme {
        GameStartEndConditionItem(
            label = "Label",
            isEnabled = true,
            onEnabledChange = {},
            content = { Text("Content") },
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewDisabled() {
    AppTheme {
        GameStartEndConditionItem(
            label = "Label",
            isEnabled = false,
            onEnabledChange = {},
            content = { Text("Content") },
        )
    }
}
