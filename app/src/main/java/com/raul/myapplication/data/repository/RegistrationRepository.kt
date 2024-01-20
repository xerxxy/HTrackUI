package com.raul.myapplication.data.repository

import com.raul.myapplication.data.remote.model.User
import com.raul.myapplication.data.remote.model.UserRegistration
import com.raul.myapplication.data.remote.network.RegistrationApiService

interface RegistrationRepository {
    suspend fun signUp(user: UserRegistration)
}

class NetworkRegistrationRepository(
    private val registrationApiService: RegistrationApiService
): RegistrationRepository {

    override suspend fun signUp(user: UserRegistration) {
        registrationApiService.signUp(user = user)
    }

}