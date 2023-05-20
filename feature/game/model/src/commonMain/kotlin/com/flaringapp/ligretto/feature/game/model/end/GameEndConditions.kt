package com.flaringapp.ligretto.feature.game.model.end

data class GameEndConditions(
    val score: GameEndScoreCondition? = null,
    val time: GameEndTimeCondition? = null,
) : GameEndCondition by GameEndCompositeCondition(
    conditions = listOfNotNull(score, time),
) {

    val isEmpty: Boolean
        get() = score == null && time == null
}
