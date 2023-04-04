package com.flaringapp.ligretto.model.end

data class GameEndConditions(
    val score: GameEndScoreCondition? = null,
    val time: GameEndTimeCondition? = null,
) : GameEndCondition by GameEndCompositeCondition(
    conditions = listOfNotNull(score, time),
)
