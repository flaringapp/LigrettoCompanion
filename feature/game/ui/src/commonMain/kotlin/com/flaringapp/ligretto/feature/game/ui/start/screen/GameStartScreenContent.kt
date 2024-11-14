package com.flaringapp.ligretto.feature.game.ui.start.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.RocketLaunch
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.flaringapp.ligretto.core.ui.components.FooterButton
import com.flaringapp.ligretto.core.ui.ext.fadingEdges
import com.flaringapp.ligretto.feature.game.ui.start.GameStartEndConditionsIntent
import com.flaringapp.ligretto.feature.game.ui.start.GameStartIntent
import com.flaringapp.ligretto.feature.game.ui.start.GameStartPlayersIntent
import com.flaringapp.ligretto.feature.game.ui.start.GameStartState
import com.flaringapp.ligretto.feature.game.ui.start.screen.endconditions.GameStartEndConditionsScope
import com.flaringapp.ligretto.feature.game.ui.start.screen.endconditions.GenericContent
import ligretto_companion.core.ui.generated.resources.back
import ligretto_companion.feature.game.ui.generated.resources.Res
import ligretto_companion.feature.game.ui.generated.resources.start_add_player
import ligretto_companion.feature.game.ui.generated.resources.start_next_step_button
import ligretto_companion.feature.game.ui.generated.resources.start_start_game
import ligretto_companion.feature.game.ui.generated.resources.start_title
import ligretto_companion.feature.game.ui.generated.resources.start_title_players
import org.jetbrains.compose.resources.stringResource
import ligretto_companion.core.ui.generated.resources.Res as CoreRes

private const val CONTENT_TYPE_END_CONDITIONS = "end_conditions"
private const val CONTENT_TYPE_PLAYERS_HEADER = "players_header"
private const val CONTENT_TYPE_PLAYER = "player"
private const val CONTENT_TYPE_ADD_PLAYER_BUTTON = "add_player_button"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun GameStartScreenContent(
    state: GameStartState,
    dispatch: (GameStartIntent) -> Unit,
    close: () -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(Res.string.start_title))
                },
                navigationIcon = {
                    IconButton(
                        onClick = close,
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = stringResource(CoreRes.string.back),
                        )
                    }
                },
            )
        },
        bottomBar = {
            ScreenFooterButton(
                state = state,
                dispatch = dispatch,
            )
        },
        content = { innerPadding ->
            ActualContent(
                modifier = Modifier
                    .consumeWindowInsets(innerPadding)
                    .padding(innerPadding),
                state = state,
                dispatch = dispatch,
            )
        },
    )
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
        modifier = modifier
            .fillMaxSize()
            .fadingEdges(PaddingValues(bottom = 24.dp)),
        contentPadding = PaddingValues(bottom = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item(key = CONTENT_TYPE_END_CONDITIONS, contentType = CONTENT_TYPE_END_CONDITIONS) {
            GameStartEndConditionsScope.GenericContent(
                modifier = Modifier.animateItem(),
                state = state.endConditions,
                dispatch = dispatch,
            )
        }

        if (!state.endConditions.isExpandedCompleted) {
            return@LazyColumn
        }

        item(key = CONTENT_TYPE_PLAYERS_HEADER, contentType = CONTENT_TYPE_PLAYERS_HEADER) {
            PlayersHeader(
                modifier = Modifier
                    .padding(top = 40.dp, bottom = 24.dp, start = 16.dp, end = 16.dp)
                    .animateItem(),
            )
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
                    Modifier.padding(bottom = 12.dp, start = 16.dp, end = 16.dp)
                }

            GameStartPlayer(
                modifier = Modifier
                    .then(paddingModifier)
                    .animateItem(),
                name = player.name,
                number = index + 1,
                canRemove = state.players.list.size > 1,
                requestFocus = player.id == state.players.focusedPlayerId,
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

        item(key = CONTENT_TYPE_ADD_PLAYER_BUTTON, contentType = CONTENT_TYPE_ADD_PLAYER_BUTTON) {
            AddPlayerButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, start = 16.dp, end = 16.dp)
                    .animateItem(),
                onClick = { dispatch(GameStartPlayersIntent.AddNew) },
            )
        }
    }
}

@Composable
private fun PlayersHeader(
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier.fillMaxWidth(),
        text = stringResource(Res.string.start_title_players),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.titleLarge,
    )
}

@Composable
private fun AddPlayerButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedButton(
        modifier = modifier,
        onClick = onClick,
    ) {
        Icon(
            modifier = Modifier.padding(end = 8.dp),
            imageVector = Icons.Rounded.Add,
            contentDescription = null,
        )

        Text(
            text = stringResource(Res.string.start_add_player),
        )
    }
}

@Composable
private fun ScreenFooterButton(
    state: GameStartState,
    dispatch: (GameStartIntent) -> Unit,
    modifier: Modifier = Modifier,
) {
    if (!state.endConditions.isExpandedCompleted) {
        NextStepButton(
            modifier = modifier,
            onClick = { dispatch(GameStartEndConditionsIntent.SubmitStep) },
        )
        return
    }

    StartGameButton(
        modifier = modifier,
        onClick = { dispatch(GameStartIntent.StartGame) },
    )
}

@Composable
private fun NextStepButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    FooterButton(
        modifier = modifier,
        onClick = onClick,
    ) {
        Text(
            text = stringResource(Res.string.start_next_step_button),
        )
    }
}

@Composable
private fun StartGameButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    FooterButton(
        modifier = modifier,
        onClick = onClick,
    ) {
        Text(
            text = stringResource(Res.string.start_start_game).uppercase(),
        )

        Icon(
            modifier = Modifier.padding(start = 8.dp),
            imageVector = Icons.Rounded.RocketLaunch,
            contentDescription = null,
        )
    }
}
