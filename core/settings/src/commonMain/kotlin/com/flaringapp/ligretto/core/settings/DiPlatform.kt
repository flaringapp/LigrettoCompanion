package com.flaringapp.ligretto.core.settings

import com.flaringapp.ligretto.core.settings.factory.ObservableSettingsFactory
import org.koin.core.scope.Scope

internal expect fun Scope.settingsFactory(): ObservableSettingsFactory
