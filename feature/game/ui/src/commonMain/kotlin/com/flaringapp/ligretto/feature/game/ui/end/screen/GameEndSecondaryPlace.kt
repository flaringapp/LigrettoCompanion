package com.flaringapp.ligretto.feature.game.ui.end.screen

import androidx.compose.foundation.layout.padding
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
internal fun GameEndSecondaryPlace(
    place: Int,
    state: PlayerResult,
    modifier: Modifier = Modifier,
) {
    GameEndGenericTopPlace(
        modifier = modifier.padding(horizontal = 16.dp),
        place = place,
        state = state,
        imageSize = 56.dp,
        nameTextStyle = MaterialTheme.typography.titleMedium,
        scoreTextStyle = MaterialTheme.typography.labelMedium,
    )
}

@Preview(showBackground = true)
@Composable
private fun Preview(
    @PreviewParameter(GameEndPlayerResultProvider::class)
    state: PlayerResult,
) {
    AppTheme {
        GameEndSecondaryPlace(
            place = 2,
            state = state,
        )
    }
}
