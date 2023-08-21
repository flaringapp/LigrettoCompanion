package com.flaringapp.ligretto.core.settings.provider

import com.flaringapp.ligretto.core.settings.factory.ObservableSettingsFactory
import com.russhwolf.settings.ObservableSettings

interface SettingsProvider {

    fun provide(type: SettingsType): ObservableSettings
}

internal class SettingsProviderImpl(
    private val factory: ObservableSettingsFactory,
) : SettingsProvider {

    override fun provide(type: SettingsType): ObservableSettings {
        return factory.create(type.settingsName)
    }
}
