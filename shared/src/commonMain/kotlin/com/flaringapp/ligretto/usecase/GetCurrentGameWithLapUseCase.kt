package com.flaringapp.ligretto.usecase

import com.flaringapp.ligretto.GameLapApplier
import com.flaringapp.ligretto.GameStorage
import com.flaringapp.ligretto.model.Game
import org.koin.core.annotation.Single
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged

interface GetCurrentGameWithLapUseCase {

    operator fun invoke(): Flow<Game?>
}

@Single
internal class GetCurrentGameWithLapUseCaseImpl(
    private val gameStorage: GameStorage,
    private val gameLapApplier: GameLapApplier,
) : GetCurrentGameWithLapUseCase {

    override fun invoke(): Flow<Game?> {
        return gameStorage.gameFlow.combine(gameStorage.lapFlow) { game, lap ->
            if (game == null) return@combine null
            if (lap == null) return@combine game
            gameLapApplier.apply(game, lap)
        }.distinctUntilChanged()
    }
}
