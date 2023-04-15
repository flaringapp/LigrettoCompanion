package com.flaringapp.ligretto.core.data

import kotlin.test.Test
import kotlin.test.assertEquals

internal class SequentialInMemoryGameIdProviderTest {

    @Test
    fun `Sequentially provided values are incremented`() {
        val provider = SequentialInMemoryGameIdProvider()
        val value1 = provider.provide()
        val value2 = provider.provide()

        assertEquals(1, value2.value - value1.value)
    }
}
