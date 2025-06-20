package com.flaringapp.ligretto.feature.game.data

import com.flaringapp.ligretto.core.di.DiDefinitionScope
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.ksp.generated.module

fun DiDefinitionScope.gameDataModule() = DataModule().module

@Module
@ComponentScan
internal class DataModule
