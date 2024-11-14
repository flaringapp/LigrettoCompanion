package com.flaringapp.ligretto.feature.game.ui.start.screen.endconditions.settings

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.flaringapp.ligretto.core.designsystem.AppTheme
import com.flaringapp.ligretto.feature.game.ui.start.screen.endconditions.GameStartEndConditionsScope
import com.flaringapp.ligretto.feature.game.ui.start.screen.endconditions.GameStartEndConditionsTimeProvider

@Preview(showBackground = true)
@Composable
private fun PreviewPadded() = with(GameStartEndConditionsScope) {
    AppTheme {
        LabeledOptionsCompact(
            modifier = Modifier.padding(16.dp),
            label = "Time",
            labelWidth = run {
                rememberTextMeasurer().measure("Time").size.width.let {
                    with(LocalDensity.current) { it.toDp() }
                }
            },
        ) {
            TimeOptions(
                state = GameStartEndConditionsTimeProvider.enabled(),
                dispatch = {},
            )
        }
    }
}
