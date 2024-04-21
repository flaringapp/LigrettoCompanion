package com.flaringapp.ligretto.core.arch

import kotlinx.coroutines.CoroutineScope

abstract class ArchViewModel {

    val viewModelScope: CoroutineScope
        get() = TODO()

    protected open fun onCleared() = Unit
}
