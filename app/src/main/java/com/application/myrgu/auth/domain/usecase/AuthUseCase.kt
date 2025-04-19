package com.application.myrgu.auth.domain.usecase

import com.application.myrgu.auth.domain.model.AuthRequest
import com.application.myrgu.auth.domain.model.AuthResponse
import com.application.myrgu.auth.domain.repository.AuthRepository
import com.application.myrgu.core.data.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    operator fun invoke(authRequest: AuthRequest): Flow<Result<AuthResponse>> =
        authRepository.auth(authRequest)
}