package com.track.myapplication.data.remote.network

import com.track.myapplication.data.remote.model.Settings
import com.track.myapplication.data.remote.model.User
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SettingsApiService {
    @GET("users/{user_id}/settings")
    suspend fun getUserSettings(userId: Long): Settings
    @POST("users/{user_id}/settings")
    suspend fun updateUserSettings(@Path(value = "user_id")userId: Long, user: User)
}