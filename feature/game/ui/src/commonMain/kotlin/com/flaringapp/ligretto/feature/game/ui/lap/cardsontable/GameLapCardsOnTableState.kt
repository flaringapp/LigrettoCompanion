package com.flaringapp.ligretto.feature.game.ui.lap.cardsontable

import com.flaringapp.ligretto.core.arch.UiState
import com.flaringapp.ligretto.core.ui.ext.UiList
import com.flaringapp.ligretto.core.ui.ext.emptyUiList
import com.flaringapp.ligretto.feature.game.ui.lap.common.player.GameLapPlayerCardsState

internal data class GameLapCardsOnTableState(
    val roundNumber: Int,
    val playerCards: UiList<GameLapPlayerCardsState> = emptyUiList(),
    val showConfirmEndLap: Boolean = false,
) : UiState
