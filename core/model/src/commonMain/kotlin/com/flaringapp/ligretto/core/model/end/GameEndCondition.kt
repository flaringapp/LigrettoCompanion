package com.flaringapp.ligretto.core.model.end

import com.flaringapp.ligretto.core.model.Game

internal interface GameEndCondition {

    fun matches(game: Game): Boolean
}
