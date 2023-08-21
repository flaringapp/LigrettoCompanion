package com.flaringapp.ligretto.core.settings.factory

import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.ObservableSettings

internal class ObservableNSUserDefaultSettingsFactory : ObservableSettingsFactory {

    override fun create(name: String?): ObservableSettings {
        return NSUserDefaultsSettings.Factory().create(name)
    }
}
