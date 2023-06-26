package com.flaringapp.ligretto.feature.game.data

import com.flaringapp.ligretto.feature.game.model.Game
import com.flaringapp.ligretto.feature.game.model.Lap
import org.koin.core.annotation.Single
import kotlinx.coroutines.flow.MutableStateFlow

internal interface GameStorage {

    val gameFlow: MutableStateFlow<Game?>

    val lapFlow: MutableStateFlow<Lap?>

    var previousGame: MutableStateFlow<Game?>
}

@Single
internal class GameStorageImpl : GameStorage {

    override val gameFlow: MutableStateFlow<Game?> = MutableStateFlow(null)

    override val lapFlow: MutableStateFlow<Lap?> = MutableStateFlow(null)

    override var previousGame: MutableStateFlow<Game?> = MutableStateFlow(null)
}
