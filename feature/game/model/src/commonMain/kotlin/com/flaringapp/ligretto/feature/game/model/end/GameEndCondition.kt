package com.flaringapp.ligretto.feature.game.model.end

import com.flaringapp.ligretto.feature.game.model.Game

internal interface GameEndCondition {

    fun matches(game: Game): Boolean
}
