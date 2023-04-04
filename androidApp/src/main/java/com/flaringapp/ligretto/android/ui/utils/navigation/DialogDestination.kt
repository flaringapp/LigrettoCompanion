package com.flaringapp.ligretto.android.ui.utils.navigation

import androidx.compose.ui.window.DialogProperties

interface DialogDestination : ScreenDestination {

    val dialogProperties: DialogProperties
}
