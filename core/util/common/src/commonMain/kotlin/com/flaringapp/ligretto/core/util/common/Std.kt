package com.flaringapp.ligretto.core.util.common

fun String.getDigits(): String {
    return filter { it.isDigit() }
}
