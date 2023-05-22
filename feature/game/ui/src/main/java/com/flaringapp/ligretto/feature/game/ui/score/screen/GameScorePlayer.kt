package com.flaringapp.ligretto.feature.game.ui.score.screen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.flaringapp.ligretto.core.designsystem.AppTheme

@Composable
fun GameScorePlayer(
    name: String,
    score: Int,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier) {
        NameText(
            modifier = Modifier
                .weight(1f)
                .alignByBaseline(),
            text = name,
        )
        ScoreText(
            modifier = Modifier.alignByBaseline(),
            score = score,
        )
    }
}

@Composable
private fun NameText(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.bodyLarge,
    )
}

@Composable
private fun ScoreText(
    score: Int,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = score.toString(),
        style = MaterialTheme.typography.titleLarge,
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewOneDigit() {
    AppTheme {
        GameScorePlayer(
            modifier = Modifier.fillMaxWidth(),
            name = "Andrii",
            score = 0,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewTwoDigits() {
    AppTheme {
        GameScorePlayer(
            modifier = Modifier.fillMaxWidth(),
            name = "Olenkka",
            score = -42,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewThreeDigits() {
    AppTheme {
        GameScorePlayer(
            modifier = Modifier.fillMaxWidth(),
            name = "Mario",
            score = 121,
        )
    }
}
