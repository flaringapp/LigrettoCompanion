package com.flaringapp.ligretto.feature.game.ui.end.screen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.flaringapp.ligretto.feature.game.ui.common.GamePlayerImage
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
        verticalAlignment = Alignment.CenterVertically,
    ) {
        PlaceText(
            place = place,
        )

        GamePlayerImage(
            modifier = Modifier.padding(start = 16.dp, end = 12.dp),
            name = state.name,
            size = 56.dp,
        )

        PlayerNameText(
            modifier = Modifier
                .weight(1f)
                .padding(end = 16.dp),
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

