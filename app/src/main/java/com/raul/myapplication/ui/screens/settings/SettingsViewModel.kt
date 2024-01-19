package com.raul.myapplication.ui.screens.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raul.myapplication.data.remote.model.MedicationBody
import com.raul.myapplication.data.remote.model.Settings
import com.raul.myapplication.data.remote.model.User
import com.raul.myapplication.data.remote.model.medical_package.MedicalRecord
import com.raul.myapplication.data.repository.SettingsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

enum class SettingsUiState{
    SUCCESS,
    LOADING,
    ERROR
}

class SettingsViewModel(
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    private val _settingsUiState = MutableStateFlow<SettingsUiState>(SettingsUiState.LOADING)
    val settingsUiState: StateFlow<SettingsUiState> = _settingsUiState.asStateFlow()

    private val _settingsUpdateUiState = MutableStateFlow<SettingsUiState>(SettingsUiState.LOADING)
    val settingsUpdateUiState: StateFlow<SettingsUiState> = _settingsUiState.asStateFlow()


    private val _settings = MutableStateFlow<Settings>(Settings(0,0))
    val settings: StateFlow<Settings> = _settings.asStateFlow()

    fun updateNewSettings(settings: Settings) {
        _settings.update { settings }
    }

    fun getSettings(userId: Long) {
        viewModelScope.launch {
            _settingsUiState.value = try {
                _settings.value = settingsRepository.getUserSettings(userId = userId)
                SettingsUiState.SUCCESS
            } catch (e: HttpException) {
                e.printStackTrace()
                SettingsUiState.LOADING
            } catch (e: IOException) {
                e.printStackTrace()
                SettingsUiState.ERROR
            }

        }
    }

    fun updateSettings(user: User) {
        viewModelScope.launch {
            _settingsUpdateUiState.value = try {
                settingsRepository.updateUserSettings(userId = user.id, user = user)
                SettingsUiState.SUCCESS
            } catch (e: HttpException) {
                e.printStackTrace()
                SettingsUiState.LOADING
            } catch (e: IOException) {
                e.printStackTrace()
                SettingsUiState.ERROR
            }
        }
    }

}