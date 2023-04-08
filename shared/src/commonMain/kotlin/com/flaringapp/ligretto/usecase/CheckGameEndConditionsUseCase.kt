package com.flaringapp.ligretto.usecase

import com.flaringapp.ligretto.model.Game
import org.koin.core.annotation.Single

interface CheckGameEndConditionsUseCase {

    operator fun invoke(game: Game): Boolean
}

@Single
internal class CheckGameEndConditionsUseCaseImpl : CheckGameEndConditionsUseCase {

    override fun invoke(game: Game): Boolean {
        return game.endConditions.matches(game)
    }
}
