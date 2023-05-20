package com.flaringapp.ligretto.feature.game.domain.usecase

import com.flaringapp.ligretto.feature.game.domain.contracts.GameRepository
import com.flaringapp.ligretto.feature.game.model.Lap
import org.koin.core.annotation.Single

interface StartLapUseCase {

    operator fun invoke(): Lap
}

@Single
internal class StartLapUseCaseImpl(
    private val repository: GameRepository,
) : StartLapUseCase {

    override fun invoke(): Lap {
        val game = requireNotNull(repository.currentGameFlow.value)
        val lap = Lap.empty(
            number = game.pendingLapNumber,
            players = game.players,
        )

        repository.startLap(lap)

        return lap
    }
}
