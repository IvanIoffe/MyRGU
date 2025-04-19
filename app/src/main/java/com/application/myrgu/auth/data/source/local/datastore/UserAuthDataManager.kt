package com.application.myrgu.auth.data.source.local.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.application.myrgu.auth.data.source.local.model.UserAuthDataLocal
import com.application.myrgu.core.data.source.local.di.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserAuthDataManager @Inject constructor(
    private val context: Context,
) {
    companion object {
        private val KEY_ID = intPreferencesKey("id")
        private val KEY_DISPLAY_NAME = stringPreferencesKey("display_name")
        private val KEY_USER_ROLE = stringPreferencesKey("user_role")
    }

    fun getUserAuthData(): Flow<UserAuthDataLocal?> {
        return context.dataStore.data.map { preferences ->
            val id = preferences[KEY_ID]
            val displayName = preferences[KEY_DISPLAY_NAME]
            val userRole = preferences[KEY_USER_ROLE]
            if (id != null && displayName != null && userRole != null)
                UserAuthDataLocal(id = id, displayName = displayName, role = userRole)
            else
                null
        }
    }

    suspend fun saveUserAuthData(userAuthDataLocal: UserAuthDataLocal) {
        context.dataStore.edit { preferences ->
            preferences[KEY_ID] = userAuthDataLocal.id
            preferences[KEY_DISPLAY_NAME] = userAuthDataLocal.displayName
            preferences[KEY_USER_ROLE] = userAuthDataLocal.role
        }
    }

    suspend fun deleteUserAuthData() {
        context.dataStore.edit { preferences ->
            preferences.remove(KEY_ID)
            preferences.remove(KEY_DISPLAY_NAME)
            preferences.remove(KEY_USER_ROLE)
        }
    }
}