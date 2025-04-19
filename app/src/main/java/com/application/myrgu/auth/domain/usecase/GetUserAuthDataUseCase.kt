package com.application.myrgu.auth.domain.usecase

import com.application.myrgu.auth.domain.model.UserAuthData
import com.application.myrgu.auth.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserAuthDataUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    operator fun invoke(): Flow<UserAuthData?> = authRepository.getUserAuthData()
}