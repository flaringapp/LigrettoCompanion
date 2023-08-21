package com.flaringapp.ligretto.feature.game.data.cache

import com.flaringapp.ligretto.feature.game.model.GameSnapshot
import org.koin.core.annotation.Single

internal interface GameCache {

    var previousGame: GameSnapshot?
}

@Single
internal class GameCacheImpl : GameCache {

    override var previousGame: GameSnapshot? = null
}
