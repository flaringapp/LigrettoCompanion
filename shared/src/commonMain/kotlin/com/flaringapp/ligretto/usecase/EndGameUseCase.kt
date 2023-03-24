package com.flaringapp.ligretto.usecase

import com.flaringapp.ligretto.GameStorage
import com.flaringapp.ligretto.model.Game
import org.koin.core.annotation.Single

interface EndGameUseCase {

    operator fun invoke(): Game?
}

@Single
internal class EndGameUseCaseImpl(
    private val gameStorage: GameStorage,
) : EndGameUseCase {

    override fun invoke(): Game? {
        return gameStorage.gameFlow.value.also {
            gameStorage.gameFlow.value = null
        }
    }
}
