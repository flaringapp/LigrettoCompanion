package com.flaringapp.ligretto.feature.game.data.cache

import com.flaringapp.ligretto.feature.game.model.Game
import org.koin.core.annotation.Single

internal interface GameCache {

    var previousGame: Game?
}

@Single
internal class GameCacheImpl : GameCache {

    override var previousGame: Game? = null
}
