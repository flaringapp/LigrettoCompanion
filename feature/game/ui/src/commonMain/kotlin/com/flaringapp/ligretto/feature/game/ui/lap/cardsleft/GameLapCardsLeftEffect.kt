package com.flaringapp.ligretto.feature.game.ui.lap.cardsleft

import com.flaringapp.ligretto.core.arch.UiEffect

internal sealed interface GameLapCardsLeftEffect : UiEffect {

    data object OpenCardsOnTable : GameLapCardsLeftEffect
}
