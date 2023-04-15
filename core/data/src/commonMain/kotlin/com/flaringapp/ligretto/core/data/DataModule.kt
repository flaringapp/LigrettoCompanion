package com.flaringapp.ligretto.core.data

import org.koin.core.KoinApplication
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.ksp.generated.module

fun KoinApplication.dataModule() = modules(DataModule().module)

@Module
@ComponentScan
internal class DataModule
