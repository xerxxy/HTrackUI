package com.raul.myapplication.ui.screens.settings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raul.myapplication.data.remote.model.MedicationBody
import com.raul.myapplication.data.remote.model.Settings
import com.raul.myapplication.data.remote.model.User
import com.raul.myapplication.data.remote.model.medical_package.MedicalRecord
import com.raul.myapplication.data.repository.SettingsRepository
import com.raul.myapplication.ui.screens.registration.RegistrationUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface SettingsUiState{
    object Success: SettingsUiState
    object Loading: SettingsUiState
    object Error: SettingsUiState
}

class SettingsViewModel(
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    var settingsUiState: SettingsUiState by mutableStateOf(SettingsUiState.Loading)
        private set

    private val _settings = MutableStateFlow<Settings>(Settings(0,0))
    val settings: StateFlow<Settings> = _settings.asStateFlow()

    fun updateNewSettings(settings: Settings) {
        _settings.update { settings }
    }

    fun getSettings(userId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            settingsUiState = try {
                _settings.value = settingsRepository.getUserSettings(userId = userId)
                SettingsUiState.Success
            } catch (e: HttpException) {
                e.printStackTrace()
                SettingsUiState.Loading
            } catch (e: IOException) {
                e.printStackTrace()
                SettingsUiState.Error
            }

        }
    }

    fun updateSettings(user: User) {
        viewModelScope.launch {
            settingsUiState = try {
                settingsRepository.updateUserSettings(userId = user.id, user = user)
                SettingsUiState.Success
            } catch (e: HttpException) {
                e.printStackTrace()
                SettingsUiState.Loading
            } catch (e: IOException) {
                e.printStackTrace()
                SettingsUiState.Error
            }
        }
    }

}