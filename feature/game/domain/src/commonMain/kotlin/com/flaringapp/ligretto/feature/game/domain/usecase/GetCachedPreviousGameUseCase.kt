package com.flaringapp.ligretto.feature.game.domain.usecase

import com.flaringapp.ligretto.feature.game.domain.contracts.GameRepository
import com.flaringapp.ligretto.feature.game.model.GameSnapshot
import org.koin.core.annotation.Single

interface GetCachedPreviousGameUseCase {

    operator fun invoke(): GameSnapshot?
}

@Single
internal class GetCachedPreviousGameUseCaseImpl(
    private val repository: GameRepository,
) : GetCachedPreviousGameUseCase {

    override fun invoke(): GameSnapshot? {
        return repository.getCachedPreviousGame()
    }
}
