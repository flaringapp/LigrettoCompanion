package com.flaringapp.ligretto.feature.home.domain

import com.flaringapp.ligretto.core.di.DiDefinitionScope
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.ksp.generated.module

fun DiDefinitionScope.homeDomainModule() = DomainModule().module

@Module
@ComponentScan
internal class DomainModule
