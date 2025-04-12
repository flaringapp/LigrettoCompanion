package com.flaringapp.ligretto.feature.game.domain.model

sealed class GameEndConditionCustomScoreValidationError {

    data object Empty : GameEndConditionCustomScoreValidationError()

    data class TooLarge(
        val maxScore: Int,
    ) : GameEndConditionCustomScoreValidationError()
}
