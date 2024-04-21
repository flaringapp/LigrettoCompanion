package com.flaringapp.ligretto.core.ui.ext

import androidx.compose.runtime.Immutable

/**
 * Special wrapper for regular [List] with compose [Immutable] promise.
 * Compose treats regular [List] as unstable. There are two ways to represent lists properly:
 * 1. use [kotlin immutable collections](https://github.com/Kotlin/kotlinx.collections.immutable);
 * 2. use custom wrapper with [Immutable] promise annotation.
 *
 * We prefer option #2 as *kotlin immutable collections* library is in alpha release, so its
 * API may change. Custom wrapper is a tiny and elegant solution for now.
 * If [kotlin immutable collections](https://github.com/Kotlin/kotlinx.collections.immutable)
 * gets stable, then it makes sense to migrate.
 *
 * **Warning!** This structure is a simple promise that the delegate list is immutable indeed.
 * Do not mutate the argument from the outside.
 */
@Immutable
class UiList<T>(
    private val delegate: List<T>,
) : List<T> by delegate {

    override fun equals(other: Any?): Boolean {
        return delegate == other
    }

    override fun hashCode(): Int {
        return delegate.hashCode()
    }

    override fun toString(): String {
        return "UiList($delegate)"
    }
}

fun <T> UiList<T>.asUiList() = this

fun <T> List<T>.asUiList() = UiList(this)

fun <T> emptyUiList() = UiList<T>(emptyList())

fun <T> uiListOf(): UiList<T> = emptyUiList()

fun <T> uiListOf(vararg elements: T): UiList<T> {
    return elements.asList().asUiList()
}
