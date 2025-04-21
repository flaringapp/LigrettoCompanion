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
private fun PreviewPadded() {
    var isScoreSelected by remember { mutableStateOf(true) }
    var isTimeSelected by remember { mutableStateOf(false) }

    AppTheme {
        GameStartEndConditionsScope.ConditionsSelectionExpanded(
            modifier = Modifier.padding(16.dp),
            scoreSelected = isScoreSelected,
            timeSelected = isTimeSelected,
            onScoreSelectionChange = { isScoreSelected = it },
            onTimeSelectionChange = { isTimeSelected = it },
        )
    }
}
