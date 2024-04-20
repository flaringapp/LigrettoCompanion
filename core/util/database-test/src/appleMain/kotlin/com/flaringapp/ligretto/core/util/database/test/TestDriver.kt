package com.flaringapp.ligretto.core.util.database.test

import app.cash.sqldelight.db.SqlDriver

internal actual fun createTestDriver(): SqlDriver {
    throw IllegalStateException("iOS target does not support test driver yet")
}
