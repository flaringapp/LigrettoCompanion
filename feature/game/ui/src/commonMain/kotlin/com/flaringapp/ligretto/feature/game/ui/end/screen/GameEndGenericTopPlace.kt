package com.flaringapp.ligretto.feature.game.ui.end.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.flaringapp.ligretto.feature.game.ui.end.GameEndState.PlayerResult
import ligretto_companion.feature.game.ui.generated.resources.Res
import ligretto_companion.feature.game.ui.generated.resources.game_end_player_score
import org.jetbrains.compose.resources.pluralStringResource

@Composable
internal fun GameEndGenericTopPlace(
    place: Int,
    state: PlayerResult,
    nameTextStyle: TextStyle,
    scoreTextStyle: TextStyle,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        PlaceIcon(
            place = place,
        )

        PlayerNameText(
            modifier = Modifier.padding(top = 12.dp),
            name = state.name,
            style = nameTextStyle,
        )

        ScoreText(
            modifier = Modifier.padding(top = 4.dp),
            score = state.score,
            style = scoreTextStyle,
        )
    }
}

@Composable
private fun PlaceIcon(
    place: Int,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = place.toString(),
    )
}

@Composable
private fun PlayerNameText(
    name: String,
    style: TextStyle,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = name,
        textAlign = TextAlign.Center,
        style = style,
    )
}

@Composable
private fun ScoreText(
    score: Int,
    style: TextStyle,
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
        textAlign = TextAlign.Center,
        style = style,
    )
}
