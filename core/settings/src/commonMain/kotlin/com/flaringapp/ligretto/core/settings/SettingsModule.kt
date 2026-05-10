package com.flaringapp.ligretto.core.settings

import com.flaringapp.ligretto.core.settings.factory.ObservableSettingsFactory
import com.flaringapp.ligretto.core.settings.provider.SettingsProvider
import com.flaringapp.ligretto.core.settings.provider.SettingsProviderImpl
import org.koin.core.annotation.Configuration
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module(includes = [SettingsPlatformModule::class])
@Configuration
class SettingsModule {

    @Single
    internal fun provideSettingsProvider(factory: ObservableSettingsFactory): SettingsProvider {
        return SettingsProviderImpl(
            factory = factory,
        )
    }
}
