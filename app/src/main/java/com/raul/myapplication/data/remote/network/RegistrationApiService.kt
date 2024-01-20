package com.raul.myapplication.data.remote.network

import com.raul.myapplication.data.remote.model.User
import com.raul.myapplication.data.remote.model.UserRegistration
import retrofit2.http.Body
import retrofit2.http.GET

import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface RegistrationApiService {

    // Account already crated, make the request to logIn
    @POST("users/login")
    suspend fun logIn(@Query("username") user: String, @Query("password") password: String): User

    @GET("users/details/{id}")
    suspend fun getData(@Path(value = "id")id: Long): User

    // Need to create account
    @POST("/users/registration")
    suspend fun signUp(@Body user: UserRegistration): Unit


}