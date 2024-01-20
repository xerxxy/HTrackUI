package com.htrackk.myapplication.data.repository

import com.htrackk.myapplication.data.remote.model.UserRegistration
import com.htrackk.myapplication.data.remote.network.RegistrationApiService

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