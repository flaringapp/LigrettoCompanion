package com.flaringapp.ligretto.core.settings

import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings
import org.koin.core.scope.Scope

internal actual fun Scope.settingsFactory(): Settings.Factory {
    return SharedPreferencesSettings.Factory(get())
}
