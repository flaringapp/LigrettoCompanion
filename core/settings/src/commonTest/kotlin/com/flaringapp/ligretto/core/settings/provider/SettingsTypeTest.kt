package com.flaringapp.ligretto.core.settings.provider

import kotlin.test.Test
import kotlin.test.assertTrue

internal class SettingsTypeTest {

    @Test
    fun `All settings types have different settings names`() {
        assertTrue {
            val values = SettingsType.values().toList()
            values == values.distinctBy { it.settingsName }
        }
    }
}
