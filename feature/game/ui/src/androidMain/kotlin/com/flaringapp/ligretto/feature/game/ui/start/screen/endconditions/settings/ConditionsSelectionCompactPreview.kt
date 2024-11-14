package com.flaringapp.ligretto.feature.game.ui.start.screen.endconditions.settings

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.flaringapp.ligretto.core.designsystem.AppTheme
import com.flaringapp.ligretto.feature.game.ui.start.screen.endconditions.GameStartEndConditionsScope

@Preview(showBackground = true)
@Composable
private fun PreviewDeselected() {
    PreviewBase(selected = false)
}

@Preview(showBackground = true)
@Composable
private fun PreviewSelected() {
    PreviewBase(selected = true)
}

@Composable
private fun PreviewBase(
    selected: Boolean,
    modifier: Modifier = Modifier,
) {
    AppTheme {
        GameStartEndConditionsScope.ConditionsSelectionCompact(
            modifier = modifier,
            scoreSelected = selected,
            timeSelected = selected,
            onScoreClick = {},
            onTimeClick = {},
        )
    }
}
