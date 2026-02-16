package com.flaringapp.ligretto.feature.game.ui.settings.screen.endconditions

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.flaringapp.ligretto.core.designsystem.AppTheme
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.preview.GameEndConditionsTimeLimitStateProvider
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.ui.options.TimeOptions

@Suppress("UnusedReceiverParameter")
@Composable
internal inline fun GameSettingsEndConditionsScope.LabeledOptions(
    label: String,
    modifier: Modifier = Modifier,
    optionsContent: @Composable () -> Unit,
) {
    Column(
        modifier = modifier,
    ) {
        LabelText(
            modifier = Modifier.padding(bottom = 8.dp, start = 16.dp, end = 16.dp),
            label = label,
        )

        optionsContent()
    }
}

@Composable
private fun LabelText(
    label: String,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = label,
        style = MaterialTheme.typography.bodySmall,
    )
}

@Preview(showBackground = true)
@Composable
private fun Preview() = with(GameSettingsEndConditionsScope) {
    AppTheme {
        LabeledOptions(
            label = "Select the target score to win the game.",
        ) {
            TimeOptions(
                state = GameEndConditionsTimeLimitStateProvider.enabled(),
                dispatch = {},
            )
        }
    }
}
