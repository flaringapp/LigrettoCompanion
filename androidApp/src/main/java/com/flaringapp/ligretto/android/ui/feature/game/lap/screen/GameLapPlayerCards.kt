package com.flaringapp.ligretto.android.ui.feature.game.lap.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.flaringapp.ligretto.android.R
import com.flaringapp.ligretto.android.ui.AppTheme

@Composable
fun GameLapPlayerCards(
    playerName: String,
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
        PlayerNameText(
            text = playerName,
        )
        Cards(
            name = stringResource(R.string.lap_cards_left),
            count = cardsLeft,
            incrementContentDescription = stringResource(R.string.lap_increment_cards_left),
            decrementContentDescription = stringResource(R.string.lap_decrement_cards_left),
            onIncrement = onIncrementCardsLeft,
            onDecrement = onDecrementCardsLeft,
        )
        Cards(
            name = stringResource(R.string.lap_cards_on_table),
            count = cardsOnTable,
            incrementContentDescription = stringResource(R.string.lap_increment_cards_on_table),
            decrementContentDescription = stringResource(R.string.lap_decrement_cards_on_table),
            onIncrement = onIncrementCardsOnTable,
            onDecrement = onDecrementCardsOnTable,
        )
    }
}

@Composable
private fun PlayerNameText(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.titleMedium,
    )
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

@Preview(showBackground = true)
@Composable
private fun PreviewSmall() {
    AppTheme {
        GameLapPlayerCards(
            playerName = "Andrii",
            cardsLeft = 4,
            cardsOnTable = 3,
            onIncrementCardsLeft = {},
            onDecrementCardsLeft = {},
            onIncrementCardsOnTable = {},
            onDecrementCardsOnTable = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewBig() {
    AppTheme {
        GameLapPlayerCards(
            playerName = "Andreolabadubidus",
            cardsLeft = 4,
            cardsOnTable = 35,
            onIncrementCardsLeft = {},
            onDecrementCardsLeft = {},
            onIncrementCardsOnTable = {},
            onDecrementCardsOnTable = {},
        )
    }
}
