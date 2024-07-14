package com.flaringapp.ligretto.feature.game.ui.lap.cardsontable

import com.flaringapp.ligretto.core.arch.UiEffect

internal sealed interface GameLapCardsOnTableEffect : UiEffect {

    data object OpenScores : GameLapCardsOnTableEffect

    data object EndGame : GameLapCardsOnTableEffect
}
