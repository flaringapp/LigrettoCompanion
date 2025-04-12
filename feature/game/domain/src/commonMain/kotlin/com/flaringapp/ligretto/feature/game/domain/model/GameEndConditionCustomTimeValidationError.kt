package com.flaringapp.ligretto.feature.game.domain.model

sealed class GameEndConditionCustomTimeValidationError {

    data object Empty : GameEndConditionCustomTimeValidationError()

    data class TooLarge(
        val maxMinutes: Int,
    ) : GameEndConditionCustomTimeValidationError()
}
