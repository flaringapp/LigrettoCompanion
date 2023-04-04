package com.flaringapp.ligretto.usecase

import com.flaringapp.ligretto.GameIdProvider
import com.flaringapp.ligretto.GameStorage
import com.flaringapp.ligretto.model.Game
import com.flaringapp.ligretto.model.GameConfig
import org.koin.core.annotation.Single
import kotlinx.datetime.Clock

interface StartGameUseCase {

    operator fun invoke(config: GameConfig)
}

@Single
internal class StartGameUseCaseImpl(
    private val gameIdProvider: GameIdProvider,
    private val gameStorage: GameStorage,
    private val clock: Clock,
) : StartGameUseCase {

    override fun invoke(config: GameConfig) {
        val id = gameIdProvider.provide()
        val game = Game(
            id = id,
            players = config.players,
            timeStarted = clock.now(),
            endConditions = config.endConditions,
        )

        gameStorage.gameFlow.value = game
    }
}
