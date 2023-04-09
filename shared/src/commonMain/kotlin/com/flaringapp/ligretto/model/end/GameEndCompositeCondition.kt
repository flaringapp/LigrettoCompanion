package com.flaringapp.ligretto.model.end

import com.flaringapp.ligretto.model.Game

internal data class GameEndCompositeCondition(
    private val conditions: List<GameEndCondition>,
) : GameEndCondition {

    override fun matches(game: Game): Boolean {
        if (conditions.isEmpty()) return false

        return conditions.all { it.matches(game) }
    }
}
