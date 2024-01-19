package com.raul.myapplication.data.repository

import com.raul.myapplication.data.remote.model.User
import com.raul.myapplication.data.remote.model.UserLogIn
import com.raul.myapplication.data.remote.network.RegistrationApiService
import retrofit2.Call
import retrofit2.Response

interface LogInRepository {
    suspend fun logIn(user: UserLogIn): User

    suspend fun getData(id: Long): User
    suspend fun signUp(user: User): Unit
}

class NetworkLogInRepository(
    private val registrationApiService: RegistrationApiService
) : LogInRepository {
    override suspend fun logIn(user: UserLogIn): User {
        return registrationApiService.logIn(user.username, user.password)
    }

    override suspend fun getData(id: Long): User {
        return registrationApiService.getData(id)
    }

    override suspend fun signUp(user: User) {
        registrationApiService.signUp(user)
    }
}