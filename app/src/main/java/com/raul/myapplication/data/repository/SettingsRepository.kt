package com.raul.myapplication.data.repository

import com.raul.myapplication.data.remote.model.Settings
import com.raul.myapplication.data.remote.model.User
import com.raul.myapplication.data.remote.network.SettingsApiService

interface SettingsRepository {
    suspend fun getUserSettings(userId: Long): Settings
    suspend fun updateUserSettings(userId: Long, user: User)
}

class NetworkSettingsRepository(
    private val settingsApiService: SettingsApiService
): SettingsRepository {
    override suspend fun getUserSettings(userId: Long): Settings {
        return settingsApiService.getUserSettings(userId = userId)
    }

    override suspend fun updateUserSettings(userId: Long, user: User) {
        settingsApiService.updateUserSettings(userId = userId, user = user)
    }
}