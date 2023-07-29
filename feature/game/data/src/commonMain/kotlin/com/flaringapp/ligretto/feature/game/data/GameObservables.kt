package com.flaringapp.ligretto.feature.game.data

import com.flaringapp.ligretto.feature.game.model.Game
import com.flaringapp.ligretto.feature.game.model.Lap
import org.koin.core.annotation.Single
import kotlinx.coroutines.flow.MutableStateFlow

internal interface GameObservables {

    val gameFlow: MutableStateFlow<Game?>

    val lapFlow: MutableStateFlow<Lap?>

}

@Single
internal class GameObservablesImpl : GameObservables {

    override val gameFlow: MutableStateFlow<Game?> = MutableStateFlow(null)

    override val lapFlow: MutableStateFlow<Lap?> = MutableStateFlow(null)

}
