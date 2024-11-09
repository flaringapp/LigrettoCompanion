package com.flaringapp.ligretto.feature.game.ui.start.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.flaringapp.ligretto.core.designsystem.AppTheme
import com.flaringapp.ligretto.feature.game.ui.start.GameStartState.EndConditions

@Preview(showBackground = true)
@Composable
private fun Preview(
    @PreviewParameter(GameStartEndConditionsProvider::class) state: EndConditions,
) {
    AppTheme {
        GameStartEndConditions(
            state = state,
            dispatch = {},
        )
    }
}
