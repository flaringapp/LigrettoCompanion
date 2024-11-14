package com.flaringapp.ligretto.feature.game.ui.start.screen.endconditions.settings

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.flaringapp.ligretto.core.designsystem.AppTheme
import com.flaringapp.ligretto.feature.game.ui.start.screen.endconditions.GameStartEndConditionsScope

@Preview(showBackground = true)
@Composable
private fun PreviewDeselected() {
    AppTheme {
        GameStartEndConditionsScope.OptionPill(
            text = "100",
            selected = false,
            onClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewSelected() {
    AppTheme {
        GameStartEndConditionsScope.OptionPill(
            text = "30m",
            selected = true,
            onClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewLeadingIcon() {
    AppTheme {
        GameStartEndConditionsScope.OptionPill(
            text = "Score",
            selected = true,
            onClick = {},
            leadingIcon = {
                Icon(
                    imageVector = Icons.Rounded.Check,
                    contentDescription = null,
                )
            },
        )
    }
}
