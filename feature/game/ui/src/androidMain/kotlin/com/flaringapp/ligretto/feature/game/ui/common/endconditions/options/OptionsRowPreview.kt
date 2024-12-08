package com.flaringapp.ligretto.feature.game.ui.common.endconditions.options

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.flaringapp.ligretto.core.designsystem.AppTheme
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.GameEndConditionsScope

@Preview(showBackground = true)
@Composable
private fun Preview() = with(GameEndConditionsScope) {
    AppTheme {
        OptionsRow {
            OptionPill(text = "50", selected = false, onClick = {})
            OptionPill(text = "100", selected = true, onClick = {})
            OptionPill(text = "150", selected = false, onClick = {})
            OptionPill(text = "Custom", selected = false, onClick = {})
        }
    }
}
