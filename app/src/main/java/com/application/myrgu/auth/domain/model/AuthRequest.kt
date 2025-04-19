package com.application.myrgu.auth.domain.model

import com.application.myrgu.auth.presentation.UserRole

data class AuthRequest(
    val login: String,
    val userRole: UserRole,
)