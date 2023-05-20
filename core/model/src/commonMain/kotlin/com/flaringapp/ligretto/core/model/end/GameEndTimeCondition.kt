package com.flaringapp.ligretto.core.model.end

import com.flaringapp.ligretto.core.model.Game
import kotlin.time.Duration
import kotlinx.datetime.Clock

data class GameEndTimeCondition(
    val gameDuration: Duration,
    val clock: Clock,
) : GameEndCondition {

    override fun matches(game: Game): Boolean {
        val now = clock.now()
        val timePassed = now - game.timeStarted
        return timePassed >= gameDuration
    }
}
