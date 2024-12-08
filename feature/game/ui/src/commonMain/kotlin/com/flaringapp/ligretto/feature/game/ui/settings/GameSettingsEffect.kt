package com.flaringapp.ligretto.feature.game.ui.settings

import com.flaringapp.ligretto.core.arch.UiEffect

internal sealed interface GameSettingsEffect : UiEffect {

    data object Close : GameSettingsEffect
}
