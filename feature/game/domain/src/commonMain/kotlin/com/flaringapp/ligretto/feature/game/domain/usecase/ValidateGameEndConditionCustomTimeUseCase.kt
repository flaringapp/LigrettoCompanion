package com.flaringapp.ligretto.feature.game.domain.usecase

import com.flaringapp.ligretto.feature.game.domain.model.GameEndConditionCustomTimeValidationError
import org.koin.core.annotation.Single

interface ValidateGameEndConditionCustomTimeUseCase {

    operator fun invoke(
        minutes: Int,
    ): GameEndConditionCustomTimeValidationError?
}

@Single
internal class ValidateGameEndConditionCustomTimeUseCaseImpl :
    ValidateGameEndConditionCustomTimeUseCase {

    companion object {
        private const val MAX_MINUTES = 300
    }

    override fun invoke(
        minutes: Int,
    ): GameEndConditionCustomTimeValidationError? {
        if (minutes <= 0) {
            return GameEndConditionCustomTimeValidationError.Empty
        }

        if (minutes > MAX_MINUTES) {
            return GameEndConditionCustomTimeValidationError.TooLarge(
                maxMinutes = MAX_MINUTES,
            )
        }

        return null
    }
}
