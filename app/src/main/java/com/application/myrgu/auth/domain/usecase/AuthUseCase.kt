package com.application.myrgu.auth.domain.usecase

import com.application.myrgu.auth.domain.model.AuthRequest
import com.application.myrgu.auth.domain.repository.AuthRepository
import javax.inject.Inject

class AuthUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    operator fun invoke(authRequest: AuthRequest) = authRepository.auth(authRequest)
}