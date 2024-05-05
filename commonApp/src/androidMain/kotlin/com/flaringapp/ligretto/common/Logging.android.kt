package com.flaringapp.ligretto.common

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

fun initLogging() {
    Napier.base(DebugAntilog())
}
