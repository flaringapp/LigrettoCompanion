package com.flaringapp.ligretto.feature.game.ui.start.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.flaringapp.ligretto.core.designsystem.AppTheme

private const val ANIM_TIME = 200

@Composable
fun GameStartEndConditionItem(
    label: String,
    isEnabled: Boolean,
    onEnabledChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable AnimatedVisibilityScope.() -> Unit,
) {
    Column(modifier = modifier) {
        Header(
            modifier = Modifier.fillMaxWidth(),
            label = label,
            isEnabled = isEnabled,
            onEnabledChange = onEnabledChange,
        )

        val fadeSpec = tween<Float>(durationMillis = ANIM_TIME)
        val sizeSpec = tween<IntSize>(durationMillis = ANIM_TIME)
        AnimatedVisibility(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            visible = isEnabled,
            enter = fadeIn(fadeSpec) + expandVertically(sizeSpec),
            exit = fadeOut(fadeSpec) + shrinkVertically(sizeSpec),
            content = content,
        )
    }
}

@Composable
private fun Header(
    label: String,
    isEnabled: Boolean,
    onEnabledChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .weight(1f),
            text = label,
            style = MaterialTheme.typography.titleMedium,
        )
        Switch(
            modifier = Modifier.padding(start = 16.dp),
            checked = isEnabled,
            onCheckedChange = onEnabledChange,
        )
    }
}

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
