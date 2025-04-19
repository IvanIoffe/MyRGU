package com.application.myrgu.auth.domain.model

import com.application.myrgu.auth.presentation.UserRole

data class UserAuthData(
    val id: Int,
    val displayName: String,
    val role: UserRole,
)