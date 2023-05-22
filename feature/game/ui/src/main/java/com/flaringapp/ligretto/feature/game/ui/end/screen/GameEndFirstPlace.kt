package com.flaringapp.ligretto.feature.game.ui.end.screen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.flaringapp.ligretto.feature.game.ui.R
import com.flaringapp.ligretto.feature.game.ui.end.GameEndState.PlayerResult
import com.flaringapp.ligretto.feature.game.ui.end.screen.preview.GameEndPlayerResultProvider
import com.flaringapp.ligretto.core.designsystem.AppTheme

@Composable
fun GameEndFirstPlace(
    state: PlayerResult,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier) {
        PlaceText()
        Text(
            modifier = Modifier
                .weight(1f)
                .alignByBaseline()
                .padding(horizontal = 24.dp),
            text = state.name,
            style = MaterialTheme.typography.titleLarge,
            maxLines = 1,
        )
        Text(
            modifier = Modifier.alignByBaseline(),
            text = state.score.toString(),
            style = MaterialTheme.typography.displaySmall,
        )
    }
}

@Composable
private fun RowScope.PlaceText() {
    Text(
        modifier = Modifier.alignByBaseline(),
        text = stringResource(R.string.game_end_first_place_one),
        style = MaterialTheme.typography.displayMedium,
    )
    Text(
        modifier = Modifier.alignByBaseline(),
        text = stringResource(R.string.game_end_first_place_two),
        style = MaterialTheme.typography.titleMedium,
    )
}

@Preview(showBackground = true)
@Composable
private fun Preview(
    @PreviewParameter(GameEndPlayerResultProvider::class) state: PlayerResult,
) {
    AppTheme {
        GameEndFirstPlace(state)
    }
}
