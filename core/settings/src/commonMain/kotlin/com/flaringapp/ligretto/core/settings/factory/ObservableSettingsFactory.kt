package com.flaringapp.ligretto.core.settings.factory

import com.russhwolf.settings.ObservableSettings

/**
 * Inherits [com.russhwolf.settings.Settings.Factory], but returns [ObservableSettings].
 */
interface ObservableSettingsFactory {

    fun create(name: String?): ObservableSettings
}
