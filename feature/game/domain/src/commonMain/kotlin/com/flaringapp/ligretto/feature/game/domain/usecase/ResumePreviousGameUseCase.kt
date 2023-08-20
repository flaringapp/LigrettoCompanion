package com.flaringapp.ligretto.feature.game.domain.usecase

import com.flaringapp.ligretto.feature.game.domain.contracts.GameRepository
import org.koin.core.annotation.Single
import kotlinx.coroutines.flow.firstOrNull

interface ResumePreviousGameUseCase {

    suspend operator fun invoke()
}

@Single
internal class ResumePreviousGameUseCaseImpl(
    private val repository: GameRepository,
) : ResumePreviousGameUseCase {

    override suspend fun invoke() {
        val activeGameId = repository.getActiveGameId() ?: return
        val gameSnapshot = repository.previousGameFlow.firstOrNull() ?: return
        if (gameSnapshot.game.id != activeGameId) return

        repository.resumeGame(gameSnapshot)
    }
}
