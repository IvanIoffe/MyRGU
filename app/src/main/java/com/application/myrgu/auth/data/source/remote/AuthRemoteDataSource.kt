package com.application.myrgu.auth.data.source.remote

import com.application.myrgu.core.data.Result
import com.application.myrgu.auth.data.source.remote.model.AuthResponseRemote
import com.application.myrgu.auth.domain.model.AuthRequest
import kotlinx.coroutines.flow.Flow

interface AuthRemoteDataSource {

    fun auth(authRequest: AuthRequest): Flow<Result<AuthResponseRemote>>
}