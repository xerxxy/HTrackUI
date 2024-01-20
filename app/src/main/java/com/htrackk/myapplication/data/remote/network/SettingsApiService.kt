package com.htrackk.myapplication.data.remote.network

import com.htrackk.myapplication.data.remote.model.Settings
import com.htrackk.myapplication.data.remote.model.User
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SettingsApiService {
    @GET("users/{user_id}/settings")
    suspend fun getUserSettings(@Path(value = "user_id")userId: Long): Settings
    @POST("users/{user_id}/settings")
    suspend fun updateUserSettings(@Path(value = "user_id")userId: Long, user: User)
}