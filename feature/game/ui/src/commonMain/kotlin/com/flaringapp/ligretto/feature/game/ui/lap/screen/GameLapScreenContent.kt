package com.flaringapp.ligretto.feature.game.ui.lap.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import com.flaringapp.ligretto.core.designsystem.AppTheme
import com.flaringapp.ligretto.core.ui.components.HeaderText
import com.flaringapp.ligretto.core.ui.misc.SnapLastItemToBottomArrangement
import com.flaringapp.ligretto.feature.game.ui.lap.GameLapIntent
import com.flaringapp.ligretto.feature.game.ui.lap.GameLapState
import com.flaringapp.ligretto.feature.game.ui.lap.screen.preview.GameLapStateProvider
import ligretto_companion.feature.game.ui.generated.resources.Res
import ligretto_companion.feature.game.ui.generated.resources.lap_end_lap
import ligretto_companion.feature.game.ui.generated.resources.lap_title
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter

private const val CONTENT_TYPE_HEADER = "header"
private const val CONTENT_TYPE_PLAYER_CARDS = "player_cards"
private const val CONTENT_TYPE_BUTTONS = "buttons"

@Composable
internal fun GameLapScreenContent(
    state: GameLapState,
    dispatch: (GameLapIntent) -> Unit,
    modifier: Modifier = Modifier,
) {
    val lastScoreIndex = remember(state.playersCards.size) {
        state.playersCards.lastIndex
    }

    if (state.showConfirmEndLap) {
        GameLapEndLapDialog(
            onConfirm = { dispatch(GameLapIntent.EndLapConfirmed) },
            onDismiss = { dispatch(GameLapIntent.HideEndLapConfirmation) },
        )
    }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 24.dp),
        verticalArrangement = remember { SnapLastItemToBottomArrangement() },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item(contentType = CONTENT_TYPE_HEADER) {
            Header(modifier = Modifier.padding(bottom = 16.dp))
        }
        itemsIndexed(
            items = state.playersCards,
            key = { _, playerCards -> "$CONTENT_TYPE_PLAYER_CARDS${playerCards.player.id}" },
            contentType = { _, _ -> CONTENT_TYPE_PLAYER_CARDS },
        ) { index, playersCards ->
            GameLapPlayerCards(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                playerName = playersCards.player.name,
                score = playersCards.score,
                cardsLeft = playersCards.cardsLeft,
                cardsOnTable = playersCards.cardsOnTable,
                onIncrementCardsLeft = {
                    dispatch(GameLapIntent.IncrementCardsLeft(playersCards.player.id))
                },
                onDecrementCardsLeft = {
                    dispatch(GameLapIntent.DecrementCardsLeft(playersCards.player.id))
                },
                onIncrementCardsOnTable = {
                    dispatch(GameLapIntent.IncrementCardsOnTable(playersCards.player.id))
                },
                onDecrementCardsOnTable = {
                    dispatch(GameLapIntent.DecrementCardsOnTable(playersCards.player.id))
                },
            )

            if (index != lastScoreIndex) {
                HorizontalDivider(Modifier.padding(horizontal = 16.dp))
            }
        }
        item(contentType = CONTENT_TYPE_BUTTONS) {
            Buttons(
                modifier = Modifier.padding(top = 16.dp),
                onEndLap = { dispatch(GameLapIntent.EndLap) },
            )
        }
    }
}

@Composable
private fun Header(
    modifier: Modifier = Modifier,
) {
    HeaderText(
        modifier = modifier,
        text = stringResource(Res.string.lap_title),
    )
}

@Composable
private fun Buttons(
    onEndLap: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxWidth(),
    ) {
        EndLapButton(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 16.dp),
            onClick = onEndLap,
        )
    }
}

@Composable
private fun EndLapButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    FilledIconButton(
        modifier = modifier,
        onClick = onClick,
    ) {
        Icon(
            painter = rememberVectorPainter(Icons.Rounded.Done),
            contentDescription = stringResource(Res.string.lap_end_lap),
        )
    }
}

@Preview
@Composable
private fun Preview(
    @PreviewParameter(GameLapStateProvider::class) state: GameLapState,
) {
    AppTheme {
        GameLapScreenContent(
            state = state,
            dispatch = {},
        )
    }
}
