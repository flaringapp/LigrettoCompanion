package com.flaringapp.ligretto.core.settings

import com.flaringapp.ligretto.core.di.DiDefinitionScope
import com.flaringapp.ligretto.core.settings.factory.ObservableSettingsFactory
import com.flaringapp.ligretto.core.settings.provider.SettingsProvider
import com.flaringapp.ligretto.core.settings.provider.SettingsProviderImpl
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import org.koin.dsl.module
import org.koin.ksp.generated.module

fun DiDefinitionScope.coreSettingsModules() = listOf(
    platformSettingModule(),
    SettingsModule().module,
)

@Module
internal class SettingsModule {

    @Single
    fun providerSettingsProvider(factory: ObservableSettingsFactory): SettingsProvider {
        return SettingsProviderImpl(
            factory = factory,
        )
    }
}

internal fun platformSettingModule() = module {
    single { settingsFactory() }
}
