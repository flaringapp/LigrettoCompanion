package com.flaringapp.ligretto.feature.game.domain.usecase

import org.koin.core.annotation.Single

interface AdvanceToNextLapUseCase {

    suspend operator fun invoke(): AdvanceToNextLapResult
}

sealed interface AdvanceToNextLapResult {
    data object NextLapStarted : AdvanceToNextLapResult
    data object GameEnded : AdvanceToNextLapResult
}

@Single
internal class AdvanceToNextLapUseCaseImpl(
    private val endLapUseCase: EndLapUseCase,
    private val startLapUseCase: StartLapUseCase,
) : AdvanceToNextLapUseCase {

    override suspend fun invoke(): AdvanceToNextLapResult {
        val game = endLapUseCase()
        if (game?.matchesEndConditions == true) {
            return AdvanceToNextLapResult.GameEnded
        }

        startLapUseCase()
        return AdvanceToNextLapResult.NextLapStarted
    }
}
