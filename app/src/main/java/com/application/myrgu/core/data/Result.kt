package com.application.myrgu.core.data

sealed class Result<out T> {
    data object Loading : Result<Nothing>()
    data object Initial : Result<Nothing>()

    data class Success<out T>(
        val data: T
    ) : Result<T>()

    data class Error(
        val message: String
    ) : Result<Nothing>()
}