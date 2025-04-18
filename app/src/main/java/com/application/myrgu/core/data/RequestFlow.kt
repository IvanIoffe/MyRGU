package com.application.myrgu.core.data

import com.application.myrgu.core.data.source.remote.model.ErrorRemote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.serialization.json.Json
import retrofit2.Response

fun <T> remoteRequestFlow(call: suspend () -> Response<T>): Flow<Result<T>> = flow {
    emit(Result.Loading)

    try {
        val response = call()

        if (response.isSuccessful) {
            response.body()?.let { data ->
                emit(Result.Success(data))
            }
        } else {
            response.errorBody()?.let { error ->
                val json = Json { ignoreUnknownKeys = true }
                val parsedError = json.decodeFromString<ErrorRemote>(error.string())
                emit(Result.Error(parsedError.message))
            }
        }
    } catch (e: Exception) {
        emit(Result.Error("Ошибка подключения. Повторите попытку позже."))
    }
}.flowOn(Dispatchers.IO)

fun <T> localRequestFlow(call: suspend () -> T): Flow<Result<T>> = flow {
    emit(Result.Loading)

    try {
        val response = call()
        emit(Result.Success(response))
    } catch (e: Exception) {
        emit(Result.Error(e.message ?: e.toString()))
    }
}.flowOn(Dispatchers.IO)