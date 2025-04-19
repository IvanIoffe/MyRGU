package com.application.myrgu.auth.domain.repository

import com.application.myrgu.core.data.Result
import com.application.myrgu.auth.domain.model.AuthRequest
import com.application.myrgu.auth.domain.model.UserAuthData
import com.application.myrgu.auth.domain.model.AuthResponse
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun auth(authRequest: AuthRequest): Flow<Result<AuthResponse>>

    fun getUserAuthData(): Flow<UserAuthData?>

    suspend fun deleteUserAuthData()
}