package com.flaringapp.ligretto.feature.game.ui.lap.common.player

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.flaringapp.ligretto.core.designsystem.AppTheme
import ligretto_companion.feature.game.ui.generated.resources.Res
import ligretto_companion.feature.game.ui.generated.resources.lap_player_cards_count_decrement
import ligretto_companion.feature.game.ui.generated.resources.lap_player_cards_count_increment
import ligretto_companion.feature.game.ui.generated.resources.lap_player_total_score
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter

@Composable
internal fun GameLapPlayerCards(
    state: GameLapPlayerCardsState,
    increment: () -> Unit,
    decrement: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        HeaderContent(
            playerName = state.playerName,
            totalScore = state.totalScore,
        )

        CardsCountContent(
            cardsCount = state.cardsCount,
            increment = increment,
            decrement = decrement,
        )
    }
}

@Composable
private fun HeaderContent(
    playerName: String,
    totalScore: Int,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surfaceContainerHighest,
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            PlayerNameText(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 16.dp),
                playerName = playerName,
            )

            TotalScore(
                score = totalScore,
            )
        }
    }
}

@Composable
private fun PlayerNameText(
    playerName: String,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = playerName,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.titleSmall,
    )
}

@Composable
private fun TotalScore(
    score: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = stringResource(Res.string.lap_player_total_score),
            style = MaterialTheme.typography.bodyMedium,
        )

        Text(
            modifier = Modifier
                .padding(start = 4.dp)
                .widthIn(min = 20.dp),
            text = score.toString(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium,
        )
    }
}

@Composable
private fun CardsCountContent(
    cardsCount: Int,
    increment: () -> Unit,
    decrement: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surfaceContainerLow,
        shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp),
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 48.dp, vertical = 24.dp),
        ) {
            FilledTonalIconButton(
                onClick = decrement,
                enabled = cardsCount > 0,
            ) {
                Icon(
                    imageVector = Icons.Rounded.Remove,
                    contentDescription = stringResource(
                        Res.string.lap_player_cards_count_decrement
                    ),
                )
            }

            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 12.dp),
                text = cardsCount.toString(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineLarge,
            )

            FilledTonalIconButton(
                onClick = increment,
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = stringResource(
                        Res.string.lap_player_cards_count_increment
                    ),
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview(
    @PreviewParameter(GameLapPlayerCardsStateProvider::class)
    state: GameLapPlayerCardsState
) {
    AppTheme {
        GameLapPlayerCards(
            state = state,
            increment = {},
            decrement = {},
        )
    }
}
