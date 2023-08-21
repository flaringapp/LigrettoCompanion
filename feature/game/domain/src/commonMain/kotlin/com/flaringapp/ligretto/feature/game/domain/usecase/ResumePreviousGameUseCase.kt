package com.flaringapp.ligretto.feature.game.domain.usecase

import com.flaringapp.ligretto.feature.game.domain.contracts.GameRepository
import com.flaringapp.ligretto.feature.game.model.GameSnapshot
import org.koin.core.annotation.Single
import kotlinx.coroutines.flow.firstOrNull

interface ResumePreviousGameUseCase {

    suspend operator fun invoke(): GameSnapshot?
}

@Single
internal class ResumePreviousGameUseCaseImpl(
    private val repository: GameRepository,
) : ResumePreviousGameUseCase {

    override suspend fun invoke(): GameSnapshot? {
        val activeGameId = repository.getActiveGameId() ?: return null
        val gameSnapshot = repository.previousGameFlow.firstOrNull() ?: return null
        if (gameSnapshot.game.id != activeGameId) return null

        repository.resumeGame(gameSnapshot)

        return gameSnapshot
    }
}
