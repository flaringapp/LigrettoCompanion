package com.flaringapp.ligretto.core.database

import app.cash.sqldelight.async.coroutines.awaitAsList
import app.cash.sqldelight.async.coroutines.awaitAsOne
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.test.runTest

internal class PlayerQueriesTest : QueriesTest<PlayerQueries>() {

    override fun provideQueries(database: Database): PlayerQueries {
        return database.playerQueries
    }

    @Test
    fun `Select all players returns all inserted players with unique ids`() = runTest {
        val expected = setOf("Andreo", "Olenkka", "Alina")

        expected.forEach {
            queries.insert(name = it, avatar = null)
        }

        val actual = queries
            .selectAll()
            .awaitAsList()

        val actualNames = actual.mapTo(HashSet()) { it.name }

        assertEquals(expected, actualNames)
        assertEquals(expected.size, actual.distinctBy { it.id }.size)
    }

    @Test
    fun `Select id by name returns id of inserted player with given name`() = runTest {
        val name = "Andreo"

        queries.insert(name = name, avatar = null)

        val expectedId = queries
            .rowid()
            .awaitAsOne()

        val actualId = queries
            .selectIdByName(name)
            .awaitAsOne()

        assertEquals(expectedId, actualId)
    }

    @Test
    fun `Select player after updating returns up to date data`() = runTest {
        val name = "Andreo"
        val updatedName = "AndreoUpdated"
        val updatedAvatar = "scout"

        queries.insert(name = name, avatar = null)

        val id = queries
            .rowid()
            .awaitAsOne()

        queries.update(
            id = id,
            name = updatedName,
            avatar = updatedAvatar,
        )

        val actual = queries
            .selectAllByIds(
                id = listOf(id),
            )
            .awaitAsOne()

        assertEquals(updatedName, actual.name)
        assertEquals(updatedAvatar, actual.avatar)
    }
}
