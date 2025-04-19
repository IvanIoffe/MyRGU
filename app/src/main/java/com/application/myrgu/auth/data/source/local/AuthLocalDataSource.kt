package com.application.myrgu.auth.data.source.local

import com.application.myrgu.auth.data.source.local.model.UserAuthDataLocal
import kotlinx.coroutines.flow.Flow

interface AuthLocalDataSource {

    fun getUserAuthData(): Flow<UserAuthDataLocal?>

    suspend fun saveUserAuthData(userAuthDataLocal: UserAuthDataLocal)

    suspend fun deleteUserAuthData()
}