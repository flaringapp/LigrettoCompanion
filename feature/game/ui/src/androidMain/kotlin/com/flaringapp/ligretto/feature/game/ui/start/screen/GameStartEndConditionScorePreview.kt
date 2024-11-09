package com.flaringapp.ligretto.feature.game.ui.start.screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.flaringapp.ligretto.core.designsystem.AppTheme

@Preview(showBackground = true)
@Composable
private fun PreviewEnabled() {
    AppTheme {
        GameStartEndConditionScore(
            modifier = Modifier.fillMaxWidth(),
            isEnabled = true,
            value = "100",
            onEnabledChange = {},
            onValueChange = {},
        )
    }
}
