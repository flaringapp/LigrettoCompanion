package com.flaringapp.ligretto.feature.game.ui.start.screen.endconditions.selection

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Scoreboard
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.flaringapp.ligretto.feature.game.ui.start.screen.endconditions.GameStartEndConditionsScope
import ligretto_companion.feature.game.ui.generated.resources.Res
import ligretto_companion.feature.game.ui.generated.resources.start_score_end_condition_top_level_expanded_message
import ligretto_companion.feature.game.ui.generated.resources.start_score_end_condition_top_level_expanded_title
import ligretto_companion.feature.game.ui.generated.resources.start_time_end_condition_top_level_expanded_message
import ligretto_companion.feature.game.ui.generated.resources.start_time_end_condition_top_level_expanded_title
import org.jetbrains.compose.resources.stringResource

@Composable
fun GameStartEndConditionsScope.ScoreSelectorExpanded(
    selected: Boolean,
    onSelectionChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    SelectionContainer(
        modifier = modifier,
        selected = selected,
        icon = Icons.Outlined.Scoreboard,
        title = stringResource(Res.string.start_score_end_condition_top_level_expanded_title),
        message = stringResource(Res.string.start_score_end_condition_top_level_expanded_message),
        onSelectionChange = onSelectionChange,
    )
}

@Composable
fun GameStartEndConditionsScope.TimeSelectorExpanded(
    selected: Boolean,
    onSelectionChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    SelectionContainer(
        modifier = modifier,
        selected = selected,
        icon = Icons.Outlined.Timer,
        title = stringResource(Res.string.start_time_end_condition_top_level_expanded_title),
        message = stringResource(Res.string.start_time_end_condition_top_level_expanded_message),
        onSelectionChange = onSelectionChange,
    )
}

@Composable
private fun SelectionContainer(
    selected: Boolean,
    icon: ImageVector,
    title: String,
    message: String,
    onSelectionChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = MaterialTheme.colorScheme

    val surfaceColor by animateColorAsState(
        label = "SurfaceColorAnimation",
        targetValue = if (selected) colors.tertiaryContainer else colors.surfaceContainer,
    )
    val contentColor by animateColorAsState(
        label = "ContentColorAnimation",
        targetValue = if (selected) colors.onSurface else colors.onSurfaceVariant,
    )
    val borderAlpha by animateFloatAsState(
        label = "BorderAlphaAnimation",
        targetValue = if (selected) 1f else 0f,
    )

    Surface(
        modifier = modifier,
        color = surfaceColor,
        contentColor = contentColor,
        border = BorderStroke(
            width = 1.dp,
            color = colors.outline.copy(alpha = borderAlpha),
        ),
        shape = RoundedCornerShape(16.dp),
        checked = selected,
        onCheckedChange = onSelectionChange,
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
        ) {
            IconAndCheckbox(
                modifier = Modifier.fillMaxWidth(),
                icon = icon,
                selected = selected,
            )

            TitleText(
                modifier = Modifier.padding(top = 24.dp),
                text = title,
            )

            MessageText(
                modifier = Modifier.padding(top = 4.dp),
                text = message,
            )
        }
    }
}

@Composable
private fun IconAndCheckbox(
    icon: ImageVector,
    selected: Boolean,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        ControlIcon(
            icon = icon,
            selected = selected,
        )

        Checkbox(
            checked = selected,
            onCheckedChange = null,
        )
    }
}

@Composable
private fun ControlIcon(
    icon: ImageVector,
    selected: Boolean,
    modifier: Modifier = Modifier,
) {
    val colors = MaterialTheme.colorScheme

    val backgroundColor by animateColorAsState(
        label = "IconBackgroundColorAnimation",
        targetValue = if (selected) colors.onTertiaryContainer else colors.surfaceDim,
    )
    val tintColor by animateColorAsState(
        label = "IconTintColorAnimation",
        targetValue = if (selected) colors.inverseOnSurface else colors.onSurfaceVariant,
    )

    Icon(
        modifier = modifier
            .background(color = backgroundColor, shape = CircleShape)
            .padding(12.dp),
        imageVector = icon,
        contentDescription = null,
        tint = tintColor,
    )
}

@Composable
private fun TitleText(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.titleSmall,
    )
}

@Composable
private fun MessageText(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.bodySmall,
    )
}
