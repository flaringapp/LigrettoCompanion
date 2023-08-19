package com.flaringapp.ligretto.feature.game.domain.usecase

import com.flaringapp.ligretto.feature.game.domain.contracts.GameRepository
import com.flaringapp.ligretto.feature.game.model.GameSnapshot
import org.koin.core.annotation.Single
import kotlinx.coroutines.flow.Flow

interface GetPreviousGameUseCase {

    operator fun invoke(): Flow<GameSnapshot?>
}

@Single
internal class GetPreviousGameUseCaseImpl(
    private val repository: GameRepository,
) : GetPreviousGameUseCase {

    override fun invoke(): Flow<GameSnapshot?> {
        return repository.previousGameFlow
    }
}
