package com.flaringapp.ligretto.feature.game.ui.lap.common.header

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import com.flaringapp.ligretto.core.designsystem.AppTheme
import com.flaringapp.ligretto.core.ui.ext.appendWhitespace
import ligretto_companion.feature.game.ui.generated.resources.Res
import ligretto_companion.feature.game.ui.generated.resources.lap_card_score_value_part_1
import ligretto_companion.feature.game.ui.generated.resources.lap_card_score_value_part_2
import org.jetbrains.compose.resources.pluralStringResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun GameLapHeader(
    phaseExplanationMessage: String,
    cardScoreValue: Int,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        PhaseExplanationText(
            modifier = Modifier.fillMaxWidth(),
            message = phaseExplanationMessage,
        )

        CardScoreValueText(
            modifier = Modifier.fillMaxWidth(),
            cardScoreValue = cardScoreValue,
        )
    }
}

@Composable
private fun PhaseExplanationText(
    message: String,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = message,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.bodyLarge,
    )
}

@Composable
private fun CardScoreValueText(
    cardScoreValue: Int,
    modifier: Modifier = Modifier,
) {
    val message = buildAnnotatedString {
        append(stringResource(Res.string.lap_card_score_value_part_1))
        appendWhitespace()

        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
            if (cardScoreValue >= 0) {
                append('+')
            }
            append(cardScoreValue.toString())

            appendWhitespace()
            append(
                pluralStringResource(
                    Res.plurals.lap_card_score_value_part_2,
                    cardScoreValue,
                    cardScoreValue,
                )
            )
        }
    }

    Text(
        modifier = modifier,
        text = message,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.bodyLarge,
    )
}

@Preview
@Composable
private fun PreviewPositive() {
    AppTheme {
        GameLapHeader(
            phaseExplanationMessage = "How many cards were played onto the communal piles?",
            cardScoreValue = 1,
        )
    }
}

@Preview
@Composable
private fun PreviewNegative() {
    AppTheme {
        GameLapHeader(
            phaseExplanationMessage = "How many cards are left in each player's hand and face-up?",
            cardScoreValue = -2,
        )
    }
}
