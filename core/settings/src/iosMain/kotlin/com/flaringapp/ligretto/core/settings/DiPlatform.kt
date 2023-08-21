package com.flaringapp.ligretto.core.settings

import com.flaringapp.ligretto.core.settings.factory.ObservableNSUserDefaultSettingsFactory
import com.flaringapp.ligretto.core.settings.factory.ObservableSettingsFactory
import org.koin.core.scope.Scope

internal actual fun Scope.settingsFactory(): ObservableSettingsFactory {
    return ObservableNSUserDefaultSettingsFactory()
}
