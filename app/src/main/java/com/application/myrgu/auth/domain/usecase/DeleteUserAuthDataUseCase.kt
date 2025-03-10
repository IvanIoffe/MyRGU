package com.application.myrgu.auth.domain.usecase

import com.application.myrgu.auth.domain.repository.AuthRepository
import javax.inject.Inject

class DeleteUserAuthDataUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke() = authRepository.deleteUserAuthData()
}