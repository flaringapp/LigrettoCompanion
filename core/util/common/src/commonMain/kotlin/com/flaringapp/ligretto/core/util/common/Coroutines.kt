package com.flaringapp.ligretto.core.util.common

import kotlinx.coroutines.Job

val Job?.isRunning: Boolean
    get() = this != null && !this.isCompleted
