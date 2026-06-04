package com.flaringapp.ligretto.feature.game.domain.usecase

import com.flaringapp.ligretto.feature.game.domain.contracts.GameRepository
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
    private val repository: GameRepository,
) : AdvanceToNextLapUseCase {

    override suspend fun invoke(): AdvanceToNextLapResult {
        val game = endLapUseCase()
        if (game?.matchesEndConditions == true) {
            return AdvanceToNextLapResult.GameEnded
        }

        repository.startNextLap()
        return AdvanceToNextLapResult.NextLapStarted
    }
}
