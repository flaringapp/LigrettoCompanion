package com.flaringapp.ligretto.model.end

import com.flaringapp.ligretto.model.Game

internal interface GameEndCondition {

    fun matches(game: Game): Boolean
}
