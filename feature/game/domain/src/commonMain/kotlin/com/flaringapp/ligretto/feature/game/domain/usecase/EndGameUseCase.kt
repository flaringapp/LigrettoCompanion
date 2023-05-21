package com.flaringapp.ligretto.feature.game.domain.usecase

import com.flaringapp.ligretto.feature.game.domain.contracts.GameRepository
import com.flaringapp.ligretto.feature.game.model.Game
import org.koin.core.annotation.Single

interface EndGameUseCase {

    operator fun invoke(): Game?
}

@Single
internal class EndGameUseCaseImpl(
    private val repository: GameRepository,
) : EndGameUseCase {

    override fun invoke(): Game? {
        return repository.endGame()
    }
}
