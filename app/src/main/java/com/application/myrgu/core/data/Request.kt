package com.application.myrgu.core.data

import retrofit2.Response

suspend fun <T> remoteRequest(call: suspend () -> Response<T>): T? {
    return try {
        val response = call()

        if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    } catch (e: Exception) {
        null
    }
}

suspend fun <T> localRequest(call: suspend () -> T): T? {
    return try {
        call()
    } catch (e: Exception) {
        null
    }
}