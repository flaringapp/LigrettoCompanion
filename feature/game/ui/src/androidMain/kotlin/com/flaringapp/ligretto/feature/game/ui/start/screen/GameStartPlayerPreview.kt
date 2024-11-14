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
            number = 1,
            canRemove = false,
            requestFocus = false,
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
            number = 4,
            canRemove = true,
            requestFocus = true,
            onNameChange = {},
            onFocusChanged = {},
            onRemoveClick = {},
        )
    }
}
