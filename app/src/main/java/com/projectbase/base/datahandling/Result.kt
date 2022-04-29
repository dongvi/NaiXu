package com.projectbase.base.datahandling

data class Result<out T>(
    val data: T?,
    val error: Throwable?
)
