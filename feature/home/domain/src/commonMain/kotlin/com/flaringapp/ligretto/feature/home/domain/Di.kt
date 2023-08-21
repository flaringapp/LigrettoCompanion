package com.flaringapp.ligretto.feature.home.domain

import org.koin.core.KoinApplication
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.ksp.generated.module

fun KoinApplication.homeDomainModule() = modules(DomainModule().module)

@Module
@ComponentScan
internal class DomainModule
