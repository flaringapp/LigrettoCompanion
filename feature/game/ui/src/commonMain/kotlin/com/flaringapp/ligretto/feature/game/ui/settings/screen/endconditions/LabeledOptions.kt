package com.flaringapp.ligretto.feature.game.ui.settings.screen.endconditions

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

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
