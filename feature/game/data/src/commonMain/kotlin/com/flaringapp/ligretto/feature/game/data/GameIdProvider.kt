package com.flaringapp.ligretto.feature.game.data

import com.flaringapp.ligretto.feature.game.model.GameId
import org.koin.core.annotation.Single

internal interface GameIdProvider {
    fun provide(): GameId
}

@Single
internal class SequentialInMemoryGameIdProvider : GameIdProvider {

    private var counter = 1

    override fun provide(): GameId {
        return GameId(counter).also { counter++ }
    }
}
