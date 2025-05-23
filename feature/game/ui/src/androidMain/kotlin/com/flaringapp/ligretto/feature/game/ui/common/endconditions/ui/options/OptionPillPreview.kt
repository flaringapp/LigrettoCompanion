package com.flaringapp.ligretto.feature.game.ui.common.endconditions.ui.options

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.flaringapp.ligretto.core.designsystem.AppTheme
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.ui.GameEndConditionsScope

@Preview(showBackground = true)
@Composable
private fun PreviewDeselected() {
    AppTheme {
        GameEndConditionsScope.OptionPill(
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
        GameEndConditionsScope.OptionPill(
            text = "30m",
            selected = true,
            onClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewSelectedFixedColor() {
    AppTheme {
        Surface(
            color = MaterialTheme.colorScheme.surfaceContainerHigh,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
        ) {
            GameEndConditionsScope.OptionPill(
                text = "30m",
                selected = true,
                onClick = {},
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewLeadingIcon() {
    AppTheme {
        GameEndConditionsScope.OptionPill(
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
