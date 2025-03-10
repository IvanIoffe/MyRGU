package com.application.myrgu.auth.domain.usecase

import com.application.myrgu.auth.domain.repository.AuthRepository
import javax.inject.Inject

class GetUserAuthDataUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    operator fun invoke() = authRepository.getUserAuthData()
}