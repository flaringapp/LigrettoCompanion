package com.flaringapp.ligretto.android.ui.feature.game.lap.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material3.Divider
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.flaringapp.ligretto.android.R
import com.flaringapp.ligretto.android.ui.feature.game.lap.GameLapIntent
import com.flaringapp.ligretto.android.ui.feature.game.lap.GameLapState
import com.flaringapp.ligretto.android.ui.feature.game.lap.screen.preview.GameLapStateProvider
import com.flaringapp.ligretto.core.designsystem.AppTheme
import com.flaringapp.ligretto.core.ui.components.HeaderText
import com.flaringapp.ligretto.core.ui.misc.SnapLastItemToBottomArrangement

private const val CONTENT_TYPE_HEADER = "header"
private const val CONTENT_TYPE_PLAYER_CARDS = "player_cards"
private const val CONTENT_TYPE_BUTTONS = "buttons"

@Composable
fun GameLapScreenContent(
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
                    dispatch(GameLapIntent.IncrementCardsLeft(playersCards.player))
                },
                onDecrementCardsLeft = {
                    dispatch(GameLapIntent.DecrementCardsLeft(playersCards.player))
                },
                onIncrementCardsOnTable = {
                    dispatch(GameLapIntent.IncrementCardsOnTable(playersCards.player))
                },
                onDecrementCardsOnTable = {
                    dispatch(GameLapIntent.DecrementCardsOnTable(playersCards.player))
                },
            )

            if (index != lastScoreIndex) {
                Divider(Modifier.padding(horizontal = 16.dp))
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
    modifier: Modifier,
) {
    HeaderText(
        modifier = modifier,
        text = stringResource(R.string.lap_title),
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
            contentDescription = stringResource(R.string.lap_end_lap),
        )
    }
}

@Preview(showBackground = true)
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
