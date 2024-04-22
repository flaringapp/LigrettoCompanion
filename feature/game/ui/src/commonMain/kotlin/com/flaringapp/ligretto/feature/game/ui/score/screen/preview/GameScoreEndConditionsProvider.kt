package com.flaringapp.ligretto.feature.game.ui.score.screen.preview

import com.flaringapp.ligretto.feature.game.ui.score.GameScoreState.EndConditions
import org.jetbrains.compose.ui.tooling.preview.PreviewParameterProvider
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

internal class GameScoreEndConditionsProvider : PreviewParameterProvider<EndConditions> {

    companion object {
        fun empty() = EndConditions()

        fun scoreOnly() = EndConditions(
            score = 120,
        )

        fun scoreAndTime() = scoreOnly().copy(
            time = EndConditions.Time(
                timeEnd = Instant.parse("2023-04-09T18:00:00.00Z"),
                clock = object : Clock {
                    override fun now(): Instant = Instant.parse("2023-04-09T17:32:26.00Z")
                }
            )
        )
    }

    override val values: Sequence<EndConditions> = sequenceOf(
        empty(),
        scoreOnly(),
        scoreAndTime(),
    )
}
