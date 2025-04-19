package com.application.myrgu.core.data.mapper

import com.application.myrgu.core.data.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun <T, R> Flow<Result<T>>.mapToDomainModelFlow(
    mapper: (T) -> R,
): Flow<Result<R>> =
    map { response ->
        when (response) {
            is Result.Success -> {
                Result.Success(mapper(response.data))
            }

            is Result.Error -> Result.Error(response.message)
            Result.Loading -> Result.Loading
            Result.Initial -> Result.Initial
        }
    }

fun <T, R> Flow<Result<T>>.mapToDomainModelFlow(
    mapper: (T) -> R,
    action: suspend (T) -> Unit,
): Flow<Result<R>> =
    map { response ->
        when (response) {
            is Result.Success -> {
                action(response.data)
                Result.Success(mapper(response.data))
            }

            is Result.Error -> Result.Error(response.message)
            Result.Loading -> Result.Loading
            Result.Initial -> Result.Initial
        }
    }