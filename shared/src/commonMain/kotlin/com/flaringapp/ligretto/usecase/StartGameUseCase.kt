package com.flaringapp.ligretto.usecase

import com.flaringapp.ligretto.GameIdProvider
import com.flaringapp.ligretto.GameStorage
import com.flaringapp.ligretto.model.Game
import com.flaringapp.ligretto.model.GameConfig
import org.koin.core.annotation.Single

interface StartGameUseCase {

    operator fun invoke(config: GameConfig)
}

@Single
internal class StartGameUseCaseImpl(
    private val gameIdProvider: GameIdProvider,
    private val gameStorage: GameStorage,
) : StartGameUseCase {

    override fun invoke(config: GameConfig) {
        val id = gameIdProvider.provide()
        val game = Game(
            id = id,
            players = config.players,
        )

        gameStorage.gameFlow.value = game
    }
}
