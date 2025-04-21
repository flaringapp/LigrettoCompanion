package com.flaringapp.ligretto.core.arch

import kotlinx.coroutines.CoroutineScope
import androidx.lifecycle.viewModelScope as viewModelScopeImpl

abstract class ViewModel : androidx.lifecycle.ViewModel() {

    // Explicitly expose scope
    val viewModelScope: CoroutineScope
        get() = viewModelScopeImpl
}
