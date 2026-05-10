package com.flaringapp.ligretto.core.settings

import com.flaringapp.ligretto.core.settings.factory.ObservableNSUserDefaultSettingsFactory
import com.flaringapp.ligretto.core.settings.factory.ObservableSettingsFactory
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
internal actual class SettingsPlatformModule {

    @Single
    fun provideSettingsFactory(): ObservableSettingsFactory {
        return ObservableNSUserDefaultSettingsFactory()
    }
}
