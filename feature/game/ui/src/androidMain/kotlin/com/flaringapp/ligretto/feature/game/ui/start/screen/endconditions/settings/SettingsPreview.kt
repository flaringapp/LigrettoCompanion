package com.flaringapp.ligretto.feature.game.ui.start.screen.endconditions.settings

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.flaringapp.ligretto.core.designsystem.AppTheme
import com.flaringapp.ligretto.feature.game.ui.start.GameStartState.EndConditions
import com.flaringapp.ligretto.feature.game.ui.start.screen.endconditions.GameStartEndConditionsProvider
import com.flaringapp.ligretto.feature.game.ui.start.screen.endconditions.GameStartEndConditionsScope

@Preview(showBackground = true)
@Composable
private fun PreviewPadded(
    @PreviewParameter(GameStartEndConditionsProvider::class)
    state: EndConditions,
) {
    AppTheme {
        GameStartEndConditionsScope.Settings(
            modifier = Modifier.padding(16.dp),
            state = state,
            dispatch = {},
        )
    }
}
