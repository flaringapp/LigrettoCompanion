package com.flaringapp.ligretto.feature.game.ui.score.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.flaringapp.ligretto.core.ui.components.Ticker
import com.flaringapp.ligretto.feature.game.ui.score.GameScoreState
import ligretto_companion.feature.game.ui.generated.resources.Res
import ligretto_companion.feature.game.ui.generated.resources.scores_score_limit
import ligretto_companion.feature.game.ui.generated.resources.scores_time_limit
import org.jetbrains.compose.resources.stringResource
import kotlin.time.Duration
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

@Composable
internal fun GameScoreEndConditions(
    state: GameScoreState.EndConditions,
    modifier: Modifier = Modifier,
) {
    if (state.score == null && state.time == null) return

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.End),
    ) {
        state.score?.let {
            ScoreLimitText(
                modifier = Modifier.weight(1f),
                score = it,
            )
        }
        state.time?.let {
            TimeLeftTicker(it)
        }
    }
}

@Composable
private fun ScoreLimitText(
    score: Int,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = stringResource(Res.string.scores_score_limit, score.toString()),
    )
}

@Composable
private fun TimeLeftTicker(
    state: GameScoreState.EndConditions.Time,
    modifier: Modifier = Modifier,
) {
    Ticker {
        val now = state.clock.now()
        val timeLeft = (state.timeEnd - now).coerceAtLeast(Duration.ZERO)

        TimeLeftText(
            modifier = modifier,
            timeLeft = timeLeft,
        )
    }
}

@Composable
private fun TimeLeftText(
    timeLeft: Duration,
    modifier: Modifier = Modifier,
) {
    val hours = timeLeft.inWholeHours.hours
    val minutes = (timeLeft - hours).inWholeMinutes.minutes
    val seconds = (timeLeft - hours - minutes).inWholeSeconds.seconds

    val text = stringResource(
        Res.string.scores_time_limit,
        hours.inWholeHours.formatTimeUnit(),
        minutes.inWholeMinutes.formatTimeUnit(),
        seconds.inWholeSeconds.formatTimeUnit(),
    )

    Text(
        modifier = modifier,
        text = text,
    )
}

private fun Long.formatTimeUnit() = toString().padStart(2, '0')
