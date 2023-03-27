package com.flaringapp.ligretto

import org.koin.core.KoinApplication
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.ksp.generated.module

fun KoinApplication.commonModule() = modules(CommonModule().module)

@Module
@ComponentScan
internal class CommonModule
