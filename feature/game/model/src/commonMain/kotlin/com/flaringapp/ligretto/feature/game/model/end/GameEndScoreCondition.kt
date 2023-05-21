package com.flaringapp.ligretto.feature.game.model.end

import com.flaringapp.ligretto.feature.game.model.Game
import com.flaringapp.ligretto.feature.game.model.Score

data class GameEndScoreCondition(
    val targetScore: Score,
) : GameEndCondition {

    override fun matches(game: Game): Boolean {
        val maxScore = game.scores.maxOfOrNull { it.value } ?: return false
        return maxScore >= targetScore
    }
}
