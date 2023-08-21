package com.flaringapp.ligretto.feature.game.data.settings

import com.flaringapp.ligretto.core.settings.provider.SettingsProvider
import com.flaringapp.ligretto.core.settings.provider.SettingsType
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.coroutines.getLongOrNullFlow
import com.russhwolf.settings.nullableLong
import org.koin.core.annotation.Single
import kotlinx.coroutines.flow.Flow

internal interface GameSettings {

    var activeGameId: Long?
    val activeGameIdFlow: Flow<Long?>
}

@OptIn(ExperimentalSettingsApi::class)
@Single
class GameSettingsImpl(
    private val settingsProvider: SettingsProvider,
) : GameSettings {

    private val settings: ObservableSettings by lazy {
        settingsProvider.provide(SettingsType.Game)
    }

    override var activeGameId: Long? by settings.nullableLong("active_game_id")
    override val activeGameIdFlow: Flow<Long?> = settings.getLongOrNullFlow("active_game_id")
}
