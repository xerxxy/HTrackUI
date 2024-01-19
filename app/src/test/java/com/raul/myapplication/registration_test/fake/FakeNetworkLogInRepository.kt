package com.raul.myapplication.registration_test.fake

import com.raul.myapplication.data.remote.model.User
import com.raul.myapplication.data.remote.model.UserLogIn
import com.raul.myapplication.data.repository.LogInRepository
import com.raul.myapplication.data.repository.NetworkLogInRepository

class FakeNetworkLogInRepository: LogInRepository{
    override suspend fun logIn(user: UserLogIn): User {
        return FakeDataSource.user
    }

    override suspend fun signUp(user: User) {
        TODO("TO BE IMPLEMENTED")
    }
}