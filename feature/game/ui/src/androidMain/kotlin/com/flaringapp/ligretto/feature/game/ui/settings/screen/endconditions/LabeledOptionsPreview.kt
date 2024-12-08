package com.flaringapp.ligretto.feature.game.ui.settings.screen.endconditions

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.flaringapp.ligretto.core.designsystem.AppTheme
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.GameEndConditionsTimeLimitStateProvider
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.ui.options.TimeOptions

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
