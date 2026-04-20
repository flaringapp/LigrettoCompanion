package com.flaringapp.ligretto.feature.game.ui.end.screen

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.flaringapp.ligretto.core.designsystem.AppTheme
import com.flaringapp.ligretto.feature.game.ui.end.GameEndState.PlayerResult
import com.flaringapp.ligretto.feature.game.ui.end.preview.GameEndPlayerResultProvider

@Composable
internal fun GameEndFirstPlace(
    state: PlayerResult,
    modifier: Modifier = Modifier,
) {
    GameEndGenericTopPlace(
        modifier = modifier,
        place = 1,
        state = state,
        imageSize = 80.dp,
        nameTextStyle = MaterialTheme.typography.headlineSmall,
        scoreTextStyle = MaterialTheme.typography.labelLarge,
    )
}

@Preview(showBackground = true)
@Composable
private fun Preview(
    @PreviewParameter(GameEndPlayerResultProvider::class)
    state: PlayerResult,
) {
    AppTheme {
        GameEndFirstPlace(state)
    }
}
