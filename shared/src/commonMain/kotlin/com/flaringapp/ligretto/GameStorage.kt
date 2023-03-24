package com.flaringapp.ligretto

import com.flaringapp.ligretto.model.Game
import com.flaringapp.ligretto.model.Lap
import org.koin.core.annotation.Single
import kotlinx.coroutines.flow.MutableStateFlow

internal interface GameStorage {

    val gameFlow: MutableStateFlow<Game?>

    val lapFlow: MutableStateFlow<Lap?>
}

@Single
internal class GameStorageImpl : GameStorage {

    override val gameFlow: MutableStateFlow<Game?> = MutableStateFlow(null)

    override val lapFlow: MutableStateFlow<Lap?> = MutableStateFlow(null)
}
