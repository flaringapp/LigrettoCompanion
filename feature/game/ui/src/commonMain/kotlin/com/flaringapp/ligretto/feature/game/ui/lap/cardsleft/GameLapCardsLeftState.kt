package com.flaringapp.ligretto.feature.game.ui.lap.cardsleft

import com.flaringapp.ligretto.core.arch.UiState
import com.flaringapp.ligretto.core.ui.ext.UiList
import com.flaringapp.ligretto.core.ui.ext.emptyUiList
import com.flaringapp.ligretto.feature.game.ui.lap.common.player.GameLapPlayerCardsState

internal data class GameLapCardsLeftState(
    val roundNumber: Int,
    val playerCards: UiList<GameLapPlayerCardsState> = emptyUiList(),
) : UiState
