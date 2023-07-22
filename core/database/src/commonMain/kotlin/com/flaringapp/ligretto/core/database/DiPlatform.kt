package com.flaringapp.ligretto.core.database

import org.koin.core.scope.Scope

internal expect fun Scope.databaseDriverFactory(
    databaseName: String,
): DatabaseDriverFactory
