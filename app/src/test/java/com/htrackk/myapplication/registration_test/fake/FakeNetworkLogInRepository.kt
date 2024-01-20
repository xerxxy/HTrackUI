package com.htrackk.myapplication.registration_test.fake

import com.htrackk.myapplication.data.remote.model.User
import com.htrackk.myapplication.data.remote.model.UserLogIn
import com.htrackk.myapplication.data.repository.LogInRepository

class FakeNetworkLogInRepository: LogInRepository{
    override suspend fun logIn(user: UserLogIn): User {
        return FakeDataSource.user
    }

    override suspend fun signUp(user: User) {
        TODO("TO BE IMPLEMENTED")
    }
}