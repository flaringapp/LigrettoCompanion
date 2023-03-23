package com.flaringapp.ligretto

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
