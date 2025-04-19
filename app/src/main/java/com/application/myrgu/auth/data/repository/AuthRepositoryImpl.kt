package com.application.myrgu.auth.data.repository

import com.application.myrgu.auth.data.mapper.toUserAuthData
import com.application.myrgu.auth.data.mapper.toUserAuthDataLocal
import com.application.myrgu.auth.data.mapper.toAuthResponse
import com.application.myrgu.auth.data.source.local.AuthLocalDataSource
import com.application.myrgu.auth.data.source.remote.AuthRemoteDataSource
import com.application.myrgu.auth.domain.model.AuthRequest
import com.application.myrgu.auth.domain.model.UserAuthData
import com.application.myrgu.auth.domain.model.AuthResponse
import com.application.myrgu.auth.domain.repository.AuthRepository
import com.application.myrgu.core.data.Result
import com.application.myrgu.core.data.mapper.mapToDomainModelFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val authLocalDataSource: AuthLocalDataSource,
) : AuthRepository {

    override fun auth(authRequest: AuthRequest): Flow<Result<AuthResponse>> {
        return authRemoteDataSource.auth(authRequest)
            .mapToDomainModelFlow(
                mapper = { authResponseRemote ->
                    authResponseRemote.toAuthResponse()
                },
                action = { authResponseRemote ->
                    authLocalDataSource.saveUserAuthData(
                        authResponseRemote.toUserAuthDataLocal(authRequest.userRole)
                    )
                }
            )
    }

    override fun getUserAuthData(): Flow<UserAuthData?> {
        return authLocalDataSource.getUserAuthData().map { userAuthDataLocal ->
            userAuthDataLocal?.toUserAuthData()
        }
    }

    override suspend fun deleteUserAuthData() {
        authLocalDataSource.deleteUserAuthData()
    }
}