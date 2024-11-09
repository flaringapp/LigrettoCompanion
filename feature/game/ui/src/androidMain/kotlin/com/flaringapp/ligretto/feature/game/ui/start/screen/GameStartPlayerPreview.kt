package com.flaringapp.ligretto.feature.game.ui.start.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.flaringapp.ligretto.core.designsystem.AppTheme

@Preview(showBackground = true)
@Composable
private fun PreviewEmpty() {
    AppTheme {
        GameStartPlayer(
            name = "",
            isFocused = false,
            onNameChange = {},
            onFocusChanged = {},
            onRemoveClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewFilled() {
    AppTheme {
        GameStartPlayer(
            name = "Player",
            isFocused = true,
            onNameChange = {},
            onFocusChanged = {},
            onRemoveClick = {},
        )
    }
}
