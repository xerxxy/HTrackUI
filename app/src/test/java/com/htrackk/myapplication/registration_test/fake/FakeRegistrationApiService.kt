package com.htrackk.myapplication.registration_test.fake

import com.htrackk.myapplication.data.remote.model.User
import com.htrackk.myapplication.data.remote.model.UserLogIn
import com.htrackk.myapplication.data.remote.network.RegistrationApiService

class FakeRegistrationApiService : RegistrationApiService {
    override fun logIn(userLogIn: UserLogIn): User {
        return FakeDataSource.user
    }

    override fun signUp(user: User) {
        TODO("Not yet implemented")
    }
}