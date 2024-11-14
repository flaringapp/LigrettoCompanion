package com.flaringapp.ligretto.feature.game.ui.start.screen.endconditions.selection

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.flaringapp.ligretto.core.designsystem.AppTheme
import com.flaringapp.ligretto.feature.game.ui.start.screen.endconditions.GameStartEndConditionsScope

@Preview(showBackground = true)
@Composable
private fun PreviewScoreDeselectedPadded() {
    var selected by remember { mutableStateOf(false) }

    AppTheme {
        GameStartEndConditionsScope.ScoreSelectorExpanded(
            modifier = Modifier.padding(16.dp),
            selected = selected,
            onSelectionChange = { selected = it },
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewTimeSelectedPadded() {
    var selected by remember { mutableStateOf(true) }

    AppTheme {
        GameStartEndConditionsScope.TimeSelectorExpanded(
            modifier = Modifier.padding(16.dp),
            selected = selected,
            onSelectionChange = { selected = it },
        )
    }
}
