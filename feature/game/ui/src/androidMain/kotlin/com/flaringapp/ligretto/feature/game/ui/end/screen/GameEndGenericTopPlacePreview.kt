package com.flaringapp.ligretto.feature.game.ui.end.screen

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.flaringapp.ligretto.core.designsystem.AppTheme
import com.flaringapp.ligretto.feature.game.ui.end.GameEndState.PlayerResult

@Preview(showBackground = true)
@Composable
private fun Preview(
    @PreviewParameter(GameEndPlayerResultProvider::class) state: PlayerResult,
) {
    AppTheme {
        GameEndGenericTopPlace(
            place = 1,
            state = state,
            nameTextStyle = MaterialTheme.typography.headlineSmall,
            scoreTextStyle = MaterialTheme.typography.labelLarge,
        )
    }
}
