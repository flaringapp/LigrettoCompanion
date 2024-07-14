package com.flaringapp.ligretto.feature.game.ui.lap.common.content

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.flaringapp.ligretto.core.designsystem.AppTheme
import com.flaringapp.ligretto.core.ui.components.FooterButton
import com.flaringapp.ligretto.core.ui.ext.UiList
import com.flaringapp.ligretto.core.ui.ext.fadingEdges
import com.flaringapp.ligretto.core.ui.ext.uiListOf
import com.flaringapp.ligretto.feature.game.ui.lap.common.header.GameLapHeader
import com.flaringapp.ligretto.feature.game.ui.lap.common.player.GameLapPlayerCards
import com.flaringapp.ligretto.feature.game.ui.lap.common.player.GameLapPlayerCardsState
import com.flaringapp.ligretto.feature.game.ui.lap.common.player.GameLapPlayerCardsStateProvider
import ligretto_companion.core.ui.generated.resources.back
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import ligretto_companion.core.ui.generated.resources.Res as CoreRes

private const val TYPE_HEADER = "header"
private const val TYPE_PLAYER_CARDS = "player_cards"

@Composable
internal fun GenericGameLapContent(
    topBarTitle: String,
    phaseExplanationMessage: String,
    cardScoreValue: Int,
    playerCards: UiList<GameLapPlayerCardsState>,
    footerButtonText: String,
    playerCardIncrement: (playerId: Long) -> Unit,
    playerCardDecrement: (playerId: Long) -> Unit,
    onFooterButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    onBackClick: (() -> Unit)? = null,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            ScreenTopAppBar(
                title = topBarTitle,
                onBackClick = onBackClick,
            )
        },
        bottomBar = {
            FooterButton(
                onClick = onFooterButtonClick,
            ) {
                Text(text = footerButtonText)
            }
        },
        content = { innerPadding ->
            ScrollableContent(
                modifier = Modifier
                    .consumeWindowInsets(innerPadding)
                    .padding(innerPadding),
                phaseExplanationMessage = phaseExplanationMessage,
                cardScoreValue = cardScoreValue,
                playerCards = playerCards,
                playerCardIncrement = playerCardIncrement,
                playerCardDecrement = playerCardDecrement,
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ScreenTopAppBar(
    title: String,
    onBackClick: (() -> Unit)?,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = title)
        },
        navigationIcon = {
            if (onBackClick == null) return@CenterAlignedTopAppBar

            IconButton(
                onClick = onBackClick,
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = stringResource(CoreRes.string.back),
                )
            }
        },
    )
}

@Composable
private fun ScrollableContent(
    phaseExplanationMessage: String,
    cardScoreValue: Int,
    playerCards: UiList<GameLapPlayerCardsState>,
    playerCardIncrement: (playerId: Long) -> Unit,
    playerCardDecrement: (playerId: Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .fadingEdges(PaddingValues(bottom = 32.dp)),
        contentPadding = PaddingValues(top = 24.dp, bottom = 32.dp, start = 16.dp, end = 16.dp)
    ) {
        item(key = TYPE_HEADER, contentType = TYPE_HEADER) {
            GameLapHeader(
                modifier = Modifier.fillMaxWidth(),
                phaseExplanationMessage = phaseExplanationMessage,
                cardScoreValue = cardScoreValue,
            )
        }

        itemsIndexed(
            items = playerCards,
            key = { _, item -> "${TYPE_PLAYER_CARDS}_${item.playerId}" },
            contentType = { _, _ -> TYPE_PLAYER_CARDS },
        ) { index, item ->
            GameLapPlayerCards(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = if (index == 0) 24.dp else 12.dp),
                state = item,
                increment = { playerCardIncrement(item.playerId) },
                decrement = { playerCardDecrement(item.playerId) },
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        GenericGameLapContent(
            topBarTitle = "Round 1.1",
            phaseExplanationMessage = "How many cards are left in each player's hand and face-up?",
            cardScoreValue = -2,
            playerCards = uiListOf(
                GameLapPlayerCardsStateProvider.zeroCards(1),
                GameLapPlayerCardsStateProvider.negativeCards(2),
                GameLapPlayerCardsStateProvider.positiveCards(3),
                GameLapPlayerCardsStateProvider.positiveCards(4),
                GameLapPlayerCardsStateProvider.zeroCards(5),
                GameLapPlayerCardsStateProvider.negativeCards(6),
                GameLapPlayerCardsStateProvider.negativeCards(7),
                GameLapPlayerCardsStateProvider.positiveCards(8),
            ),
            footerButtonText = "Next",
            playerCardDecrement = {},
            playerCardIncrement = {},
            onFooterButtonClick = {},
            onBackClick = {},
        )
    }
}
