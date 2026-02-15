package com.flaringapp.ligretto.feature.game.model.end

import com.flaringapp.ligretto.feature.game.model.Game
import kotlin.time.Clock
import kotlin.time.Duration

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
