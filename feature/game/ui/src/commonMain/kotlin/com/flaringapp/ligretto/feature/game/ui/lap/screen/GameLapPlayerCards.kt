package com.flaringapp.ligretto.feature.game.ui.lap.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.VectorPainter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.flaringapp.ligretto.core.designsystem.AppTheme
import ligretto_companion.feature.game.ui.generated.resources.Res
import ligretto_companion.feature.game.ui.generated.resources.lap_cards_left
import ligretto_companion.feature.game.ui.generated.resources.lap_cards_on_table
import ligretto_companion.feature.game.ui.generated.resources.lap_decrement_cards_left
import ligretto_companion.feature.game.ui.generated.resources.lap_decrement_cards_on_table
import ligretto_companion.feature.game.ui.generated.resources.lap_increment_cards_left
import ligretto_companion.feature.game.ui.generated.resources.lap_increment_cards_on_table
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun GameLapPlayerCards(
    playerName: String,
    score: Int,
    cardsLeft: Int,
    cardsOnTable: Int,
    onIncrementCardsLeft: () -> Unit,
    onDecrementCardsLeft: () -> Unit,
    onIncrementCardsOnTable: () -> Unit,
    onDecrementCardsOnTable: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        PlayerNameAndScore(
            playerName = playerName,
            score = score,
        )
        Cards(
            name = stringResource(Res.string.lap_cards_left),
            count = cardsLeft,
            incrementContentDescription = stringResource(Res.string.lap_increment_cards_left),
            decrementContentDescription = stringResource(Res.string.lap_decrement_cards_left),
            onIncrement = onIncrementCardsLeft,
            onDecrement = onDecrementCardsLeft,
        )
        Cards(
            name = stringResource(Res.string.lap_cards_on_table),
            count = cardsOnTable,
            incrementContentDescription = stringResource(Res.string.lap_increment_cards_on_table),
            decrementContentDescription = stringResource(Res.string.lap_decrement_cards_on_table),
            onIncrement = onIncrementCardsOnTable,
            onDecrement = onDecrementCardsOnTable,
        )
    }
}

@Composable
private fun PlayerNameAndScore(
    playerName: String,
    score: Int,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(end = 24.dp),
            text = playerName,
            style = MaterialTheme.typography.titleMedium,
        )
        Text(
            modifier = Modifier,
            text = score.toString(),
            style = MaterialTheme.typography.titleMedium,
        )
    }
}

@Composable
private fun Cards(
    name: String,
    count: Int,
    incrementContentDescription: String,
    decrementContentDescription: String,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CardsOperationNameText(
            modifier = Modifier.weight(1f),
            text = name,
        )
        CardsOperationButton(
            isEnabled = count > 0,
            icon = rememberVectorPainter(Icons.Rounded.Remove),
            contentDescription = decrementContentDescription,
            onClick = onDecrement,
        )
        CardsOperationCountText(
            count = count,
        )
        CardsOperationButton(
            icon = rememberVectorPainter(Icons.Rounded.Add),
            contentDescription = incrementContentDescription,
            onClick = onIncrement,
        )
    }
}

@Composable
private fun CardsOperationNameText(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.bodyMedium,
    )
}

@Composable
private fun CardsOperationCountText(
    count: Int,
    modifier: Modifier = Modifier,
) {
    val style = MaterialTheme.typography.titleLarge
    val textWidth = with(LocalDensity.current) { style.fontSize.toDp() } * 2

    Text(
        modifier = modifier.width(textWidth),
        text = count.toString(),
        textAlign = TextAlign.Center,
        style = style,
    )
}

@Composable
private fun CardsOperationButton(
    icon: VectorPainter,
    contentDescription: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
) {
    FilledIconButton(
        modifier = modifier,
        enabled = isEnabled,
        onClick = onClick,
    ) {
        Icon(
            painter = icon,
            contentDescription = contentDescription,
        )
    }
}

@Preview
@Composable
private fun PreviewSmall() {
    AppTheme {
        GameLapPlayerCards(
            playerName = "Andrii",
            score = 2,
            cardsLeft = 4,
            cardsOnTable = 3,
            onIncrementCardsLeft = {},
            onDecrementCardsLeft = {},
            onIncrementCardsOnTable = {},
            onDecrementCardsOnTable = {},
        )
    }
}

@Preview
@Composable
private fun PreviewBig() {
    AppTheme {
        GameLapPlayerCards(
            playerName = "Andreolabadubidus",
            score = 152,
            cardsLeft = 4,
            cardsOnTable = 35,
            onIncrementCardsLeft = {},
            onDecrementCardsLeft = {},
            onIncrementCardsOnTable = {},
            onDecrementCardsOnTable = {},
        )
    }
}
