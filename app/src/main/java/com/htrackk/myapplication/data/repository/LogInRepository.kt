package com.htrackk.myapplication.data.repository

import com.htrackk.myapplication.data.remote.model.User
import com.htrackk.myapplication.data.remote.model.UserLogIn
import com.htrackk.myapplication.data.remote.model.UserRegistration
import com.htrackk.myapplication.data.remote.network.RegistrationApiService

interface LogInRepository {
    suspend fun logIn(user: UserLogIn): User

    suspend fun getData(id: Long): User
    suspend fun signUp(user: UserRegistration): Unit
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

    override suspend fun signUp(user: UserRegistration) {
        registrationApiService.signUp(user)
    }
}