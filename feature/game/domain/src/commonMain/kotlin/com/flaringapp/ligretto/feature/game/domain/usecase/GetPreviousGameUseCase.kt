package com.flaringapp.ligretto.feature.game.domain.usecase

import com.flaringapp.ligretto.feature.game.domain.contracts.GameRepository
import com.flaringapp.ligretto.feature.game.model.Game
import org.koin.core.annotation.Single
import kotlinx.coroutines.flow.StateFlow

interface GetPreviousGameUseCase {

    operator fun invoke(): StateFlow<Game?>
}

@Single
internal class GetPreviousGameUseCaseImpl(
    private val repository: GameRepository,
) : GetPreviousGameUseCase {

    override fun invoke(): StateFlow<Game?> {
        return repository.previousGame
    }
}
