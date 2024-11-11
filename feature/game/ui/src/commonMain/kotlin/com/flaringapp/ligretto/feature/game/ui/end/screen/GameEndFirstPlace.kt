package com.flaringapp.ligretto.feature.game.ui.end.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.flaringapp.ligretto.feature.game.ui.end.GameEndState.PlayerResult
import ligretto_companion.feature.game.ui.generated.resources.Res
import ligretto_companion.feature.game.ui.generated.resources.game_end_player_score
import org.jetbrains.compose.resources.pluralStringResource

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
        ActualContent(
            state = state,
        )
    }
}

@Composable
private fun ActualContent(
    state: PlayerResult,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        PlayerNameText(
            name = state.name,
        )

        ScoreText(
            modifier = Modifier.padding(top = 4.dp),
            score = state.score,
        )
    }
}

@Composable
private fun PlayerNameText(
    name: String,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = name,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.headlineSmall,
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
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.labelLarge,
    )
}
