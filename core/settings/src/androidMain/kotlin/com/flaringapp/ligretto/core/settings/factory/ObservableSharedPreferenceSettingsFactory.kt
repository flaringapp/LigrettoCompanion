package com.flaringapp.ligretto.core.settings.factory

import android.content.Context
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.SharedPreferencesSettings

internal class ObservableSharedPreferenceSettingsFactory(
    private val context: Context,
) : ObservableSettingsFactory {

    override fun create(name: String?): ObservableSettings {
        return SharedPreferencesSettings.Factory(context).create(name)
    }
}
