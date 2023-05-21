package com.flaringapp.ligretto.core.navigation

import androidx.compose.ui.window.DialogProperties

interface DialogDestination : ScreenDestination {

    val dialogProperties: DialogProperties
}
