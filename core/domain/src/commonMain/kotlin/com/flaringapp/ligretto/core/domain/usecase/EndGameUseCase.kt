package com.flaringapp.ligretto.core.domain.usecase

import com.flaringapp.ligretto.core.model.Game
import com.flaringapp.ligretto.domain.contracts.GameRepository
import org.koin.core.annotation.Single

interface EndGameUseCase {

    operator fun invoke(): Game?
}

@Single
internal class EndGameUseCaseImpl(
    private val repository: GameRepository,
) : EndGameUseCase {

    override fun invoke(): Game? {
        return repository.currentGameFlow.value.also {
            repository.setCurrentGame(null)
            repository.setCurrentLap(null)
        }
    }
}
