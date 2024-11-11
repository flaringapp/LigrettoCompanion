package com.flaringapp.ligretto.feature.game.ui.end.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.flaringapp.ligretto.feature.game.ui.end.GameEndState.PlayerResult

@Composable
internal fun GameEndFirstPlace(
    state: PlayerResult,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.surfaceContainer,
    ) {
        GameEndGenericTopPlace(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp),
            place = 1,
            state = state,
            imageSize = 80.dp,
            nameTextStyle = MaterialTheme.typography.headlineSmall,
            scoreTextStyle = MaterialTheme.typography.labelLarge,
        )
    }
}
