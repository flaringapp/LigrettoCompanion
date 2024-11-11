package com.flaringapp.ligretto.feature.game.ui.end.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.flaringapp.ligretto.feature.game.ui.end.GameEndState.PlayerResult
import ligretto_companion.feature.game.ui.generated.resources.Res
import ligretto_companion.feature.game.ui.generated.resources.game_end_player_score
import org.jetbrains.compose.resources.pluralStringResource

@Composable
internal fun GameEndOtherPlace(
    place: Int,
    state: PlayerResult,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        PlaceText(
            place = place,
        )

        PlayerNameText(
            modifier = Modifier.weight(1f),
            name = state.name,
        )

        ScoreText(
            score = state.score,
        )
    }
}

@Composable
private fun PlaceText(
    place: Int,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = place.toString(),
        style = MaterialTheme.typography.titleMedium,
    )
}

@Composable
private fun PlayerNameText(
    name: String,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = name,
        style = MaterialTheme.typography.titleMedium,
    )
}

@Composable
private fun ScoreText(
    score: Int,
    modifier: Modifier = Modifier,
) {
    val text = pluralStringResource(
        Res.plurals.game_end_player_score,
        score,
        score,
    )

    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.labelMedium,
    )
}

