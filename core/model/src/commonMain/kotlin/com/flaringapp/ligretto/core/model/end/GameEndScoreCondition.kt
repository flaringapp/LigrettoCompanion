package com.flaringapp.ligretto.core.model.end

import com.flaringapp.ligretto.core.model.Game
import com.flaringapp.ligretto.core.model.Score

data class GameEndScoreCondition(
    val targetScore: Score,
) : GameEndCondition {

    override fun matches(game: Game): Boolean {
        val maxScore = game.scores.maxOfOrNull { it.value } ?: return false
        return maxScore >= targetScore
    }
}

