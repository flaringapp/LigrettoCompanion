package com.flaringapp.ligretto.feature.game.ui.common.endconditions.ui.options

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.flaringapp.ligretto.core.designsystem.AppFixedColorScheme
import com.flaringapp.ligretto.core.designsystem.AppTheme
import com.flaringapp.ligretto.core.designsystem.fixedOrDynamicContentColorFor
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.ui.GameEndConditionsScope

@Suppress("UnusedReceiverParameter")
@Composable
internal fun GameEndConditionsScope.OptionPill(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    leadingIcon: (@Composable () -> Unit)? = null,
) {
    val selectionAlphaAnimation by animateFloatAsState(
        label = "SelectionAlphaAnimation",
        targetValue = if (selected) 1f else 0f,
    )

    val colorScheme = MaterialTheme.colorScheme
    val selectedSurfaceColor = when {
        LocalContentColor.current == colorScheme.onSurfaceVariant -> {
            AppFixedColorScheme.SecondaryFixedDim
        }

        else -> {
            colorScheme.secondaryContainer
        }
    }

    val contentColor by animateColorAsState(
        label = "ContentColorAnimation",
        targetValue = if (selected) {
            fixedOrDynamicContentColorFor(selectedSurfaceColor)
        } else {
            LocalContentColor.current
        },
    )

    Surface(
        modifier = modifier.heightIn(min = 32.dp),
        color = selectedSurfaceColor.copy(alpha = selectionAlphaAnimation),
        contentColor = contentColor,
        border = BorderStroke(
            width = 1.dp,
            color = colorScheme.outline.copy(alpha = 1f - selectionAlphaAnimation),
        ),
        shape = CircleShape,
        onClick = onClick,
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            leadingIcon?.invoke()

            Text(
                text = text,
                style = MaterialTheme.typography.labelLarge,
            )
        }
    }
}

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
