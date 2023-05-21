package com.flaringapp.ligretto.feature.game.model.end

import com.flaringapp.ligretto.feature.game.model.Game

internal data class GameEndCompositeCondition(
    private val conditions: List<GameEndCondition>,
) : GameEndCondition {

    override fun matches(game: Game): Boolean {
        if (conditions.isEmpty()) return false

        return conditions.all { it.matches(game) }
    }
}
