package com.flaringapp.ligretto.core.settings

import com.flaringapp.ligretto.core.settings.factory.ObservableSettingsFactory
import com.flaringapp.ligretto.core.settings.factory.ObservableSharedPreferenceSettingsFactory
import org.koin.core.scope.Scope

internal actual fun Scope.settingsFactory(): ObservableSettingsFactory {
    return ObservableSharedPreferenceSettingsFactory(get())
}
