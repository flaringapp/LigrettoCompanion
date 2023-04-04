package com.flaringapp.ligretto.model

import com.flaringapp.ligretto.model.end.GameEndConditions

data class GameConfig(
    val players: List<Player>,
    val endConditions: GameEndConditions = GameEndConditions(),
)
