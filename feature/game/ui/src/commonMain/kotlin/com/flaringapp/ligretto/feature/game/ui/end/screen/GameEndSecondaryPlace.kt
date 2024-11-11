package com.flaringapp.ligretto.feature.game.ui.end.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.flaringapp.ligretto.feature.game.ui.end.GameEndState.PlayerResult

@Composable
internal fun GameEndSecondaryPlace(
    place: Int,
    state: PlayerResult,
    modifier: Modifier = Modifier,
) {
    GameEndGenericTopPlace(
        modifier = modifier.padding(horizontal = 16.dp),
        place = place,
        state = state,
        nameTextStyle = MaterialTheme.typography.titleMedium,
        scoreTextStyle = MaterialTheme.typography.labelMedium,
    )
}
