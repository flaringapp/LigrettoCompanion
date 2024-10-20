package com.flaringapp.ligretto.feature.game.ui.lap.common.content

import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.flaringapp.ligretto.core.designsystem.AppTheme
import com.flaringapp.ligretto.core.ui.components.FooterButton
import com.flaringapp.ligretto.core.ui.ext.UiList
import com.flaringapp.ligretto.core.ui.ext.appendWhitespace
import com.flaringapp.ligretto.core.ui.ext.fadingEdges
import com.flaringapp.ligretto.core.ui.ext.uiListOf
import com.flaringapp.ligretto.feature.game.ui.lap.common.player.GameLapPlayerCards
import com.flaringapp.ligretto.feature.game.ui.lap.common.player.GameLapPlayerCardsState
import com.flaringapp.ligretto.feature.game.ui.lap.common.player.GameLapPlayerCardsStateProvider
import ligretto_companion.core.ui.generated.resources.back
import ligretto_companion.feature.game.ui.generated.resources.Res
import ligretto_companion.feature.game.ui.generated.resources.lap_card_score_delta_part_1
import ligretto_companion.feature.game.ui.generated.resources.lap_card_score_delta_part_2
import ligretto_companion.feature.game.ui.generated.resources.lap_round_number
import org.jetbrains.compose.resources.pluralStringResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import ligretto_companion.core.ui.generated.resources.Res as CoreRes

private const val TYPE_CARD_SCORE_DELTA = "card_score_delta"
private const val TYPE_PLAYER_CARDS = "player_cards"

@Composable
internal fun GenericGameLapContent(
    roundNumber: Int,
    topBarTitle: String,
    cardScoreDelta: Int,
    playerCards: UiList<GameLapPlayerCardsState>,
    footerButtonText: String,
    playerCardIncrement: (playerId: Long) -> Unit,
    playerCardDecrement: (playerId: Long) -> Unit,
    onFooterButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    onBackClick: (() -> Unit)? = null,
) {
    val contentListState = rememberLazyListState()

    Scaffold(
        modifier = modifier,
        topBar = {
            ScreenTopAppBar(
                roundNumber = roundNumber,
                title = topBarTitle,
                onBackClick = onBackClick,
                isElevatedTransition = updateTransition(
                    label = "IsElevatedTransition",
                    targetState = contentListState.canScrollBackward,
                ),
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
                state = contentListState,
                cardScoreDelta = cardScoreDelta,
                playerCards = playerCards,
                playerCardIncrement = playerCardIncrement,
                playerCardDecrement = playerCardDecrement,
            )
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ScreenTopAppBar(
    roundNumber: Int,
    title: String,
    onBackClick: (() -> Unit)?,
    isElevatedTransition: Transition<Boolean>,
) {
    val elevation by isElevatedTransition.animateDp(
        label = "ShadowElevation",
        targetValueByState = { if (it) 4.dp else 0.dp },
    )

    CenterAlignedTopAppBar(
        modifier = Modifier.graphicsLayer {
            shadowElevation = elevation.toPx()
        },
        title = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = stringResource(Res.string.lap_round_number, roundNumber),
                    style = MaterialTheme.typography.titleSmall,
                )

                Text(
                    text = title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
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
    state: LazyListState,
    cardScoreDelta: Int,
    playerCards: UiList<GameLapPlayerCardsState>,
    playerCardIncrement: (playerId: Long) -> Unit,
    playerCardDecrement: (playerId: Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .fadingEdges(PaddingValues(bottom = 24.dp)),
        state = state,
        contentPadding = PaddingValues(top = 24.dp, bottom = 24.dp, start = 16.dp, end = 16.dp),
    ) {
        item(key = TYPE_CARD_SCORE_DELTA, contentType = TYPE_CARD_SCORE_DELTA) {
            CardScoreDeltaText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                cardScoreDelta = cardScoreDelta,
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
                    .padding(top = if (index == 0) 0.dp else 12.dp),
                state = item,
                increment = { playerCardIncrement(item.playerId) },
                decrement = { playerCardDecrement(item.playerId) },
            )
        }
    }
}

@Composable
private fun CardScoreDeltaText(
    cardScoreDelta: Int,
    modifier: Modifier = Modifier,
) {
    val text = buildAnnotatedString {
        append(
            stringResource(Res.string.lap_card_score_delta_part_1),
        )

        appendWhitespace()

        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
            append(
                pluralStringResource(
                    Res.plurals.lap_card_score_delta_part_2,
                    cardScoreDelta,
                    cardScoreDelta,
                ),
            )
        }
    }

    Text(
        modifier = modifier,
        text = text,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.bodyLarge,
    )
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        GenericGameLapContent(
            roundNumber = 1,
            topBarTitle = "Cards on table",
            cardScoreDelta = -2,
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
