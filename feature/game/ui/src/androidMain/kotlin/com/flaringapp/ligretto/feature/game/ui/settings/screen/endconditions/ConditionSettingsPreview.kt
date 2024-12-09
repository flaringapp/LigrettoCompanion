package com.flaringapp.ligretto.feature.game.ui.settings.screen.endconditions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.flaringapp.ligretto.core.designsystem.AppTheme
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.GameEndConditionsTimeLimitStateProvider
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.ui.options.TimeOptions

@Preview(showBackground = true)
@Composable
private fun Preview() = with(GameSettingsEndConditionsScope) {
    var selected by remember { mutableStateOf(true) }

    AppTheme {
        ConditionSettings(
            conditionName = "Play for a Set Score",
            selected = selected,
            onSelectionChanged = { selected = it },
        ) {
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
}
