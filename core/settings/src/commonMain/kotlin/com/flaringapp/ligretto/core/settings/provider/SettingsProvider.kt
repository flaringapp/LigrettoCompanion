package com.flaringapp.ligretto.core.settings.provider

import com.russhwolf.settings.Settings

interface SettingsProvider {

    fun provide(type: SettingsType): Settings
}

internal class SettingsProviderImpl(
    private val factory: Settings.Factory,
) : SettingsProvider {

    override fun provide(type: SettingsType): Settings {
        return factory.create(type.settingsName)
    }
}
