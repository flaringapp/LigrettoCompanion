package com.flaringapp.ligretto.feature.game.domain.usecase

import com.flaringapp.ligretto.feature.game.domain.contracts.GameRepository
import com.flaringapp.ligretto.feature.game.model.Game
import org.koin.core.annotation.Single

interface GetCachedPreviousGameUseCase {

    operator fun invoke(): Game?
}

@Single
internal class GetCachedPreviousGameUseCaseImpl(
    private val repository: GameRepository,
) : GetCachedPreviousGameUseCase {

    override fun invoke(): Game? {
        return repository.getCachedPreviousGame()
    }
}
