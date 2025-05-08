package com.flaringapp.ligretto.common.di

import android.content.Context
import com.flaringapp.ligretto.core.di.DiDefinitionScope
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.dsl.module
import org.koin.test.verify.verify
import org.junit.Test

class KoinConfigurationCheckTest {

    @OptIn(KoinExperimentalAPI::class)
    @Test
    fun `Verify koin configuration is complete`() {
        module {
            includes(DiDefinitionScope.appModules())
        }.verify(
            extraTypes = listOf(
                Context::class,
                // TODO remove - @InjectedParam not supported in verify API yet
                Boolean::class,
            ),
        )
    }
}
