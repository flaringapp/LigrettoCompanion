package com.flaringapp.ligretto.core.settings

import org.koin.core.KoinApplication
import org.koin.dsl.module

fun KoinApplication.coreSettingsModule() = modules(
    platformSettingModule(),
)

internal fun platformSettingModule() = module {

    single { settingsFactory() }
}
