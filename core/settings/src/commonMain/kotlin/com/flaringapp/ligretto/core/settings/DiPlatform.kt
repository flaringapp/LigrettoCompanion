package com.flaringapp.ligretto.core.settings

import com.russhwolf.settings.Settings
import org.koin.core.scope.Scope

internal expect fun Scope.settingsFactory(): Settings.Factory
