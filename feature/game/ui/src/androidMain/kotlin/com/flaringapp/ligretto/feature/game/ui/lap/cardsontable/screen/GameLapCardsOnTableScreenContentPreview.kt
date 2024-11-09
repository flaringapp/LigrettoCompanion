package com.flaringapp.ligretto.feature.game.ui.lap.cardsontable.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.flaringapp.ligretto.core.designsystem.AppTheme
import com.flaringapp.ligretto.feature.game.ui.lap.cardsontable.GameLapCardsOnTableState

@Preview
@Composable
private fun Preview(
    @PreviewParameter(GameLapCardsOnTableStateProvider::class)
    state: GameLapCardsOnTableState,
) {
    AppTheme {
        GameLapCardsOnTableScreenContent(
            state = state,
            dispatch = {},
            onBackClick = {},
        )
    }
}
