package com.flaringapp.ligretto.feature.game.domain.usecase

import com.flaringapp.ligretto.feature.game.domain.contracts.GameRepository
import com.flaringapp.ligretto.feature.game.model.Game
import org.koin.core.annotation.Single
import kotlinx.coroutines.flow.StateFlow

interface GetCurrentGameUseCase {

    operator fun invoke(): StateFlow<Game?>
}

@Single
internal class GetCurrentGameUseCaseImpl(
    private val repository: GameRepository,
) : GetCurrentGameUseCase {

    override fun invoke(): StateFlow<Game?> {
        return repository.currentGameFlow
    }
}
