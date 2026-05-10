package com.flaringapp.ligretto.core.settings.factory

import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.Settings

/**
 * Inherits [Settings.Factory], but returns [ObservableSettings].
 */
internal interface ObservableSettingsFactory : Settings.Factory {

    override fun create(name: String?): ObservableSettings
}
