package com.flaringapp.ligretto.core.settings

import android.content.Context
import com.flaringapp.ligretto.core.settings.factory.ObservableSettingsFactory
import com.flaringapp.ligretto.core.settings.factory.ObservableSharedPreferenceSettingsFactory
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
internal actual class SettingsPlatformModule {

    @Single
    fun provideSettingsFactory(
        context: Context,
    ): ObservableSettingsFactory {
        return ObservableSharedPreferenceSettingsFactory(context)
    }
}
