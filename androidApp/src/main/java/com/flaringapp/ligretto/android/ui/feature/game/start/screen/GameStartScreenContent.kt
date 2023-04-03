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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.flaringapp.ligretto.android.R
import com.flaringapp.ligretto.android.ui.AppTheme
import com.flaringapp.ligretto.android.ui.common.HeaderText
import com.flaringapp.ligretto.android.ui.feature.game.start.GameStartIntent
import com.flaringapp.ligretto.android.ui.feature.game.start.GameStartState
import com.flaringapp.ligretto.android.ui.feature.game.start.screen.preview.GameStartStateProvider
import com.flaringapp.ligretto.android.ui.utils.SnapLastItemToBottomArrangement

private const val CONTENT_TYPE_HEADER = "header"
private const val CONTENT_TYPE_PLAYER = "player"
private const val CONTENT_TYPE_BUTTONS = "buttons"

@Composable
fun GameStartScreenContent(
    state: GameStartState,
    dispatch: (GameStartIntent) -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        if (state.isEmpty) {
            EmptyScreen(modifier = Modifier.align(Alignment.Center))
        }
        ActualContent(state, dispatch)
    }
}

@Composable
private fun EmptyScreen(
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier.padding(horizontal = 32.dp),
        text = stringResource(R.string.players_empty),
        style = MaterialTheme.typography.bodyMedium,
    )
}

@Composable
private fun ActualContent(
    state: GameStartState,
    dispatch: (GameStartIntent) -> Unit,
    modifier: Modifier = Modifier,
) {
    val lastPlayersIndex = remember(state.players.size) {
        state.players.lastIndex
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
            items = state.players,
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
                isFocused = player.id == state.focusedPlayerId,
                onNameChange = { name ->
                    dispatch(GameStartIntent.ChangePlayerName(player.id, name))
                },
                onFocusChanged = { isFocused ->
                    dispatch(GameStartIntent.PlayerFocusChanged(player.id, isFocused))
                },
                onRemoveClick = {
                    dispatch(GameStartIntent.RemovePlayer(player.id))
                },
            )
        }
        item(contentType = CONTENT_TYPE_BUTTONS) {
            Buttons(
                modifier = Modifier.padding(top = 16.dp),
                onAddPlayer = { dispatch(GameStartIntent.AddNewPlayer) },
                onStartGame = { dispatch(GameStartIntent.StartGame) },
                canStartGame = !state.isEmpty,
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
        text = stringResource(R.string.players_title),
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
            text = stringResource(R.string.players_add_player),
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
            contentDescription = stringResource(R.string.players_start_game),
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
