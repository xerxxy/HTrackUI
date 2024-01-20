package com.raul.myapplication

import com.raul.myapplication.registration_test.fake.FakeDataSource
import com.raul.myapplication.registration_test.fake.FakeNetworkLogInRepository
import com.raul.myapplication.registration_test.rules.TestDispatcherRule
import com.raul.myapplication.ui.screens.login.LogInUiState
import com.raul.myapplication.ui.screens.login.LogInViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class LogInViewModelTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun logInViewModel_logIn_verifyLogInUiStateSuccess() =
        runTest {
            val logInViewModel = LogInViewModel(
                logInRepository = FakeNetworkLogInRepository()
            )
            logInViewModel.logIn(FakeDataSource.userLogin)
            assertEquals(
                LogInUiState.Success(FakeDataSource.user),
                logInViewModel.logInUiState
            )
        }

}