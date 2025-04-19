package com.application.myrgu.auth.data.source.remote

import com.application.myrgu.auth.data.mapper.toPathParam
import com.application.myrgu.auth.data.source.remote.model.AuthResponseRemote
import com.application.myrgu.auth.data.source.remote.service.AuthApiService
import com.application.myrgu.auth.domain.model.AuthRequest
import com.application.myrgu.core.data.Result
import com.application.myrgu.core.data.remoteRequestFlow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RetrofitAuthRemoteDataSource @Inject constructor(
    private val authApiService: AuthApiService,
) : AuthRemoteDataSource {

    override fun auth(authRequest: AuthRequest): Flow<Result<AuthResponseRemote>> {
        return remoteRequestFlow {
            authApiService.auth(
                login = authRequest.login,
                authEndpoint = authRequest.userRole.toPathParam(),
            )
        }
    }
}