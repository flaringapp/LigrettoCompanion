package com.flaringapp.ligretto.core.settings

import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings
import org.koin.core.scope.Scope

internal actual fun Scope.settingsFactory(): Settings.Factory {
    return NSUserDefaultsSettings.Factory()
}
