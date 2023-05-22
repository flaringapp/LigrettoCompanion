package com.flaringapp.ligretto.feature.game.ui.end.screen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.flaringapp.ligretto.feature.game.ui.end.GameEndState.PlayerResult
import com.flaringapp.ligretto.feature.game.ui.end.screen.preview.GameEndPlayerResultProvider
import com.flaringapp.ligretto.core.designsystem.AppTheme

@Composable
fun GameEndOtherPlace(
    state: PlayerResult,
    placeNumber: String,
    placePrefix: String,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier) {
        PlaceText(
            placeNumber = placeNumber,
            placePrefix = placePrefix,
        )
        Text(
            modifier = Modifier
                .weight(1f)
                .alignByBaseline()
                .padding(horizontal = 24.dp),
            text = state.name,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 1,
        )
        Text(
            modifier = Modifier.alignByBaseline(),
            text = state.score.toString(),
            style = MaterialTheme.typography.headlineSmall,
        )
    }
}

@Composable
private fun RowScope.PlaceText(
    placeNumber: String,
    placePrefix: String,
) {
    Text(
        modifier = Modifier.alignByBaseline(),
        text = placeNumber,
        style = MaterialTheme.typography.headlineMedium,
    )
    Text(
        modifier = Modifier.alignByBaseline(),
        text = placePrefix,
        style = MaterialTheme.typography.titleSmall,
    )
}

@Preview(showBackground = true)
@Composable
private fun Preview(
    @PreviewParameter(GameEndPlayerResultProvider::class) state: PlayerResult,
) {
    AppTheme {
        GameEndOtherPlace(
            state,
            placeNumber = "2",
            placePrefix = "nd",
        )
    }
}
