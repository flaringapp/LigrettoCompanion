package com.flaringapp.ligretto.core.ui.ext

import androidx.compose.runtime.Immutable

@Immutable
class UiList<T>(delegate: List<T>) : List<T> by delegate

fun <T> List<T>.asUiList() = UiList(this)

fun <T> emptyUiList() = UiList<T>(emptyList())

fun <T> uiListOf(vararg elements: T): UiList<T> {
    if (elements.isEmpty()) return emptyUiList()
    return elements.asList().asUiList()
}
