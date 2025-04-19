package com.application.myrgu.auth.data.source.remote.service

import com.application.myrgu.auth.data.source.remote.model.AuthResponseRemote
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AuthApiService {
    @GET("auth_by_{endpoint}/{login}")
    suspend fun auth(
        @Path("endpoint") authEndpoint: String,
        @Path("login") login: String,
    ): Response<AuthResponseRemote>
}