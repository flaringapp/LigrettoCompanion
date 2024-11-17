package com.flaringapp.ligretto.feature.game.ui.end.screen

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.flaringapp.ligretto.feature.game.ui.end.GameEndState.PlayerResult

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
