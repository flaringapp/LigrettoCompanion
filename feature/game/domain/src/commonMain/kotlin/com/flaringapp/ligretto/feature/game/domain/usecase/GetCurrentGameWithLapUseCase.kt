package com.flaringapp.ligretto.feature.game.domain.usecase

import com.flaringapp.ligretto.feature.game.domain.GameLapApplier
import com.flaringapp.ligretto.feature.game.domain.contracts.GameRepository
import com.flaringapp.ligretto.feature.game.model.Game
import org.koin.core.annotation.Single
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged

interface GetCurrentGameWithLapUseCase {

    operator fun invoke(): Flow<Game?>
}

@Single
internal class GetCurrentGameWithLapUseCaseImpl(
    private val repository: GameRepository,
    private val gameLapApplier: GameLapApplier,
) : GetCurrentGameWithLapUseCase {

    override fun invoke(): Flow<Game?> {
        return repository.currentGameFlow.combine(repository.currentLapFlow) { game, lap ->
            if (game == null) return@combine null
            if (lap == null) return@combine game
            gameLapApplier.apply(game, lap)
        }.distinctUntilChanged()
    }
}
