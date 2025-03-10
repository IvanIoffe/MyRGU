package com.application.myrgu.auth.data.mapper

import com.application.myrgu.auth.data.source.local.model.UserAuthDataLocal
import com.application.myrgu.auth.data.source.remote.model.AuthResponseRemote
import com.application.myrgu.auth.domain.model.UserAuthData
import com.application.myrgu.auth.domain.model.AuthResponse
import com.application.myrgu.auth.presentation.UserRole

fun AuthResponseRemote.toAuthResponse() =
    AuthResponse(
        id = id,
        displayName = displayName,
    )

fun AuthResponseRemote.toUserAuthDataLocal(userRole: UserRole) =
    UserAuthDataLocal(
        id = id,
        displayName = displayName,
        role = userRole.name,
    )

fun UserAuthData.toUserAuthDataLocal() =
    UserAuthDataLocal(
        id = id,
        displayName = displayName,
        role = role.name,
    )

fun UserAuthDataLocal.toUserAuthData() =
    UserAuthData(
        id = id,
        displayName = displayName,
        role = UserRole.valueOf(role),
    )

fun UserRole.toPathParam(): String {
    return when(this) {
        UserRole.GROUP -> "groupnumber"
        UserRole.TEACHER -> "teacherlogin"
    }
}