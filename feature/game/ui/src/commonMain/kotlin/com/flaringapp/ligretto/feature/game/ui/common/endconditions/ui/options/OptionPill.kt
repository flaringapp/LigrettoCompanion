package com.flaringapp.ligretto.feature.game.ui.common.endconditions.ui.options

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.flaringapp.ligretto.core.designsystem.AppFixedColorScheme
import com.flaringapp.ligretto.core.designsystem.fixedOrDynamicContentColorFor
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.ui.GameEndConditionsScope

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
    val surfaceColor = when {
        LocalContentColor.current == colorScheme.onSurfaceVariant -> {
            AppFixedColorScheme.SecondaryFixedDim
        }

        else -> {
            colorScheme.secondaryContainer
        }
    }
    val contentColor = fixedOrDynamicContentColorFor(surfaceColor)

    Surface(
        modifier = modifier.heightIn(min = 32.dp),
        color = surfaceColor.copy(alpha = selectionAlphaAnimation),
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
