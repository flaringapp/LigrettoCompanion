package com.flaringapp.ligretto.feature.game.ui.score.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.flaringapp.ligretto.core.ui.components.Ticker
import com.flaringapp.ligretto.feature.game.ui.score.GameScoreState
import kotlin.time.Duration

@Composable
internal fun GameScoreEndConditionContent(
    state: GameScoreState.EndConditions,
    modifier: Modifier = Modifier,
) {
    if (state.score == null && state.time == null) return

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        state.score?.let {
            GameScoreEndConditionScore(
                modifier = Modifier.weight(1f),
                score = it,
            )
        }

        state.time?.let { time ->
            Ticker {
                val now = time.clock.now()
                val timeLeft = (time.timeEnd - now).coerceAtLeast(Duration.ZERO)

                GameScoreEndConditionTime(
                    modifier = Modifier.weight(1f),
                    minutes = timeLeft.inWholeMinutes.toInt(),
                )
            }
        }
    }
}
