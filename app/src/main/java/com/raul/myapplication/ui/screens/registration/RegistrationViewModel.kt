package com.raul.myapplication.ui.screens.registration

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raul.myapplication.data.remote.model.User
import com.raul.myapplication.data.remote.model.UserLogIn
import com.raul.myapplication.data.remote.model.UserRegistration
import com.raul.myapplication.data.repository.RegistrationRepository
import com.raul.myapplication.ui.screens.medication.MedicationUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


sealed interface RegistrationUiState {
    object Success: RegistrationUiState
    object Loading: RegistrationUiState
    object Error: RegistrationUiState
}
class RegistrationViewModel(
    private val registrationRepository: RegistrationRepository
): ViewModel() {

    private val _user = MutableStateFlow(UserRegistration( "", "", "", "", "", "", "", "", ""))
    val user: StateFlow<UserRegistration> = _user.asStateFlow()

    var registrationUiState: RegistrationUiState by mutableStateOf(RegistrationUiState.Loading)
        private set
    fun signUp() {
        viewModelScope.launch {
            registrationUiState = try {
                registrationRepository.signUp(user = _user.value)
                RegistrationUiState.Success
            } catch (e: HttpException) {
                e.printStackTrace()
                RegistrationUiState.Error
            } catch (e: IOException) {
                e.printStackTrace()
                RegistrationUiState.Error
            }
        }
    }

    fun updateUser(user: UserRegistration) {
        _user.update { user }
    }


}