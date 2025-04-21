package com.flaringapp.ligretto.core.ui.ext

import androidx.compose.ui.text.AnnotatedString

fun AnnotatedString.Builder.appendWhitespace(): AnnotatedString.Builder {
    return append(' ')
}
