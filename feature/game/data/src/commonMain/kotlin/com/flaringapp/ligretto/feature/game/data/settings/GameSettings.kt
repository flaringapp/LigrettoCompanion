package com.flaringapp.ligretto.feature.game.data.settings

import com.flaringapp.ligretto.core.settings.provider.SettingsProvider
import com.flaringapp.ligretto.core.settings.provider.SettingsType
import com.russhwolf.settings.Settings
import com.russhwolf.settings.nullableLong
import org.koin.core.annotation.Single

internal interface GameSettings {

    var activeGameId: Long?
}

@Single
class GameSettingsImpl(
    private val settingsProvider: SettingsProvider,
): GameSettings {

    private val settings: Settings by lazy { settingsProvider.provide(SettingsType.Game) }

    override var activeGameId: Long? by settings.nullableLong("active_game_id")
}
