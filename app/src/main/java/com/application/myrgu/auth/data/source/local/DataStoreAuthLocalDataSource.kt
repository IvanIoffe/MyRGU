package com.application.myrgu.auth.data.source.local

import com.application.myrgu.auth.data.source.local.datastore.UserAuthDataManager
import com.application.myrgu.auth.data.source.local.model.UserAuthDataLocal
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DataStoreAuthLocalDataSource @Inject constructor(
    private val userAuthDataManager: UserAuthDataManager,
) : AuthLocalDataSource {

    override fun getUserAuthData(): Flow<UserAuthDataLocal?> {
        return userAuthDataManager.getUserAuthData()
    }

    override suspend fun saveUserAuthData(userAuthDataLocal: UserAuthDataLocal) {
        userAuthDataManager.saveUserAuthData(userAuthDataLocal)
    }

    override suspend fun deleteUserAuthData() {
        userAuthDataManager.deleteUserAuthData()
    }
}