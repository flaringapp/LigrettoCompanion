package com.flaringapp.ligretto.feature.game.domain.usecase

import com.flaringapp.ligretto.feature.game.domain.model.GameEndConditionCustomScoreValidationError
import org.koin.core.annotation.Single

interface ValidateGameEndConditionCustomScoreUseCase {

    operator fun invoke(
        score: Int,
    ): GameEndConditionCustomScoreValidationError?
}

@Single
internal class ValidateGameEndConditionCustomScoreUseCaseImpl :
    ValidateGameEndConditionCustomScoreUseCase {

    companion object {
        private const val MAX_SCORE = 1000
    }

    override fun invoke(
        score: Int,
    ): GameEndConditionCustomScoreValidationError? {
        if (score <= 0) {
            return GameEndConditionCustomScoreValidationError.Empty
        }

        if (score > MAX_SCORE) {
            return GameEndConditionCustomScoreValidationError.TooLarge(
                maxScore = MAX_SCORE,
            )
        }

        return null
    }
}
