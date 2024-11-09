package com.flaringapp.ligretto.feature.game.ui.end.screen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.flaringapp.ligretto.feature.game.ui.end.GameEndState.PlayerResult
import ligretto_companion.feature.game.ui.generated.resources.Res
import ligretto_companion.feature.game.ui.generated.resources.end_first_place_one
import ligretto_companion.feature.game.ui.generated.resources.end_first_place_two
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun GameEndFirstPlace(
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
        text = stringResource(Res.string.end_first_place_one),
        style = MaterialTheme.typography.displayMedium,
    )
    Text(
        modifier = Modifier.alignByBaseline(),
        text = stringResource(Res.string.end_first_place_two),
        style = MaterialTheme.typography.titleMedium,
    )
}
