package com.flaringapp.ligretto.android.ui.feature.game.start.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.flaringapp.ligretto.android.R
import com.flaringapp.ligretto.android.ui.feature.game.start.GameStartIntent
import com.flaringapp.ligretto.android.ui.feature.game.start.GameStartPlayersIntent
import com.flaringapp.ligretto.android.ui.feature.game.start.GameStartState
import com.flaringapp.ligretto.android.ui.feature.game.start.screen.preview.GameStartStateProvider
import com.flaringapp.ligretto.core.designsystem.AppTheme
import com.flaringapp.ligretto.core.ui.components.HeaderText
import com.flaringapp.ligretto.core.ui.misc.SnapLastItemToBottomArrangement

private const val CONTENT_TYPE_HEADER = "header"
private const val CONTENT_TYPE_END_CONDITIONS = "end_conditions"
private const val CONTENT_TYPE_EMPTY_PLAYERS = "empty_players"
private const val CONTENT_TYPE_PLAYER = "player"
private const val CONTENT_TYPE_BUTTONS = "buttons"

private const val KEY_HEADER_END_CONDITIONS = "_end_conditions"
private const val KEY_HEADER_PLAYERS = "_players"

@Composable
fun GameStartScreenContent(
    state: GameStartState,
    dispatch: (GameStartIntent) -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        ActualContent(state, dispatch)
    }
}

@Composable
private fun ActualContent(
    state: GameStartState,
    dispatch: (GameStartIntent) -> Unit,
    modifier: Modifier = Modifier,
) {
    val lastPlayersIndex = remember(state.players.list.size) {
        state.players.list.lastIndex
    }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 24.dp),
        verticalArrangement = remember { SnapLastItemToBottomArrangement() },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item(
            contentType = CONTENT_TYPE_HEADER,
            key = "$CONTENT_TYPE_HEADER$KEY_HEADER_END_CONDITIONS",
        ) {
            EndConditionsHeader(modifier = Modifier.padding(bottom = 16.dp))
        }
        item(contentType = CONTENT_TYPE_END_CONDITIONS) {
            GameStartEndConditions(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 32.dp),
                state = state.endConditions,
                dispatch = dispatch,
            )
        }
        item(
            contentType = CONTENT_TYPE_HEADER,
            key = "$CONTENT_TYPE_HEADER$KEY_HEADER_PLAYERS",
        ) {
            PlayersHeader(modifier = Modifier.padding(bottom = 16.dp))
        }
        if (state.players.list.isEmpty()) {
            item(contentType = CONTENT_TYPE_EMPTY_PLAYERS) {
                EmptyPlayers()
            }
        }
        itemsIndexed(
            items = state.players.list,
            key = { _, player -> "$CONTENT_TYPE_PLAYER${player.id}" },
            contentType = { _, _ -> CONTENT_TYPE_PLAYER },
        ) { index, player ->
            val paddingModifier =
                if (index == lastPlayersIndex) {
                    Modifier.padding(horizontal = 16.dp)
                } else {
                    Modifier.padding(start = 16.dp, end = 16.dp, bottom = 12.dp)
                }

            GameStartPlayer(
                modifier = Modifier.then(paddingModifier),
                name = player.name,
                isFocused = player.id == state.players.focusedPlayerId,
                onNameChange = { name ->
                    dispatch(GameStartPlayersIntent.ChangeName(player.id, name))
                },
                onFocusChanged = { isFocused ->
                    dispatch(GameStartPlayersIntent.FocusChanged(player.id, isFocused))
                },
                onRemoveClick = {
                    dispatch(GameStartPlayersIntent.Remove(player.id))
                },
            )
        }
        item(contentType = CONTENT_TYPE_BUTTONS) {
            Buttons(
                modifier = Modifier.padding(top = 16.dp),
                onAddPlayer = { dispatch(GameStartPlayersIntent.AddNew) },
                onStartGame = { dispatch(GameStartIntent.StartGame) },
                canStartGame = state.players.list.isNotEmpty(),
            )
        }
    }
}

@Composable
private fun EndConditionsHeader(
    modifier: Modifier,
) {
    HeaderText(
        modifier = modifier,
        text = stringResource(R.string.start_title_end_conditions),
    )
}

@Composable
private fun PlayersHeader(
    modifier: Modifier,
) {
    HeaderText(
        modifier = modifier,
        text = stringResource(R.string.start_title_players),
    )
}

@Composable
private fun EmptyPlayers(
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp, vertical = 64.dp),
        text = stringResource(R.string.start_empty),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.bodyMedium,
    )
}

@Composable
private fun Buttons(
    canStartGame: Boolean,
    onAddPlayer: () -> Unit,
    onStartGame: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxWidth(),
    ) {
        AddPlayerButton(
            modifier = Modifier.align(Alignment.Center),
            onClick = onAddPlayer,
        )
        StartGameButton(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 16.dp),
            isEnabled = canStartGame,
            onClick = onStartGame,
        )
    }
}

@Composable
private fun AddPlayerButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        modifier = modifier,
        onClick = onClick,
    ) {
        Text(
            text = stringResource(R.string.start_add_player),
        )
    }
}

@Composable
private fun StartGameButton(
    isEnabled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    FilledIconButton(
        modifier = modifier,
        enabled = isEnabled,
        onClick = onClick,
    ) {
        Icon(
            painter = rememberVectorPainter(Icons.Rounded.KeyboardArrowRight),
            contentDescription = stringResource(R.string.start_start_game),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview(
    @PreviewParameter(GameStartStateProvider::class) state: GameStartState
) {
    AppTheme {
        GameStartScreenContent(
            state = state,
            dispatch = {},
        )
    }
}
