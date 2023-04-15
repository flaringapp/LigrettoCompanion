package com.flaringapp.ligretto.core.domain.usecase

import com.flaringapp.ligretto.core.model.Lap
import com.flaringapp.ligretto.domain.contracts.GameRepository
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

        repository.setCurrentLap(lap)

        return lap
    }
}
