package com.flaringapp.ligretto.core.settings.test.provider

import com.flaringapp.ligretto.core.settings.provider.SettingsProvider
import com.flaringapp.ligretto.core.settings.provider.SettingsType
import com.russhwolf.settings.MapSettings
import com.russhwolf.settings.Settings

class TestSettingsProvider : SettingsProvider {

    override fun provide(type: SettingsType): Settings {
        return MapSettings()
    }
}
