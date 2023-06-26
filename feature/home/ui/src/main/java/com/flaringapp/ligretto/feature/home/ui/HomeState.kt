package com.flaringapp.ligretto.feature.home.ui

import com.flaringapp.ligretto.core.arch.UiState

internal data class HomeState(
    val hasPreviousGame: Boolean = false,
) : UiState
