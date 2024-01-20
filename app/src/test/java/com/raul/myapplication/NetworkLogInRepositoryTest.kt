package com.raul.myapplication

import com.raul.myapplication.data.repository.NetworkLogInRepository
import com.raul.myapplication.registration_test.fake.FakeDataSource
import com.raul.myapplication.registration_test.fake.FakeRegistrationApiService
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class NetworkLogInRepositoryTest {

    @Test
    fun networkLogInRepository_logIn_verifyUserReturn() =
        runTest {
            val repository = NetworkLogInRepository(
                registrationApiService = FakeRegistrationApiService()
            )
            assertEquals(FakeDataSource.user, repository.logIn(FakeDataSource.userLogin))
        }

}