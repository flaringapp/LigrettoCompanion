package com.flaringapp.ligretto.core.database

import org.koin.core.scope.Scope

internal actual fun Scope.databaseDriverFactory(
    databaseName: String,
): DatabaseDriverFactory {
    return DatabaseDriverFactory(
        databaseName = databaseName,
    )
}
