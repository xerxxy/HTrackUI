package com.track.myapplication.ui.screens.emergency_contact

import com.track.myapplication.data.remote.model.EmergencyContact
import com.track.myapplication.data.repository.EmergencyContactRepository
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.track.myapplication.data.local.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


sealed interface EmergencyContactUiState {
    object Success : EmergencyContactUiState

    object Error : EmergencyContactUiState

    object Loading : EmergencyContactUiState
}

class EmergencyContactViewModel(
    private val emergencyContactRepository: EmergencyContactRepository,
    private val offlineUserRepository: UserRepository
) : ViewModel() {

    var emergencyContactUiState: EmergencyContactUiState by mutableStateOf(EmergencyContactUiState.Loading)
        private set

    private val _emergencyContactList = MutableStateFlow(emptyList<EmergencyContact>())
    val emergencyContactList: StateFlow<List<EmergencyContact>> = _emergencyContactList.asStateFlow()



    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _newEmergencyContact = MutableStateFlow(EmergencyContact("", "00", 0))
    val newEmergencyContact = _newEmergencyContact.asStateFlow()
    fun getEmergencyContactList(userId: Long) {
        viewModelScope.launch(Dispatchers.Main) {
            emergencyContactUiState = try {
                _isLoading.value = true
                val response = emergencyContactRepository.getEmergencyContacts(userId = userId)
                _emergencyContactList.value = response
                EmergencyContactUiState.Success
            } catch (e: HttpException) {
                e.printStackTrace()
                EmergencyContactUiState.Error
            } catch (e: IOException) {
                e.printStackTrace()
                EmergencyContactUiState.Error
            }
            _isLoading.value = false
        }
    }

    fun deleteEmergencyContact(userId: Long, emergencyContact: EmergencyContact) {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                emergencyContactRepository.deleteEmergencyContact(
                    userId = userId,
                    contactId = emergencyContact.id
                )
            } catch (e: HttpException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            _emergencyContactList.update {
                val mutableList = it.toMutableList()
                mutableList.remove(emergencyContact)
                mutableList
            }
        }
    }

    fun updateNewContact(emergencyContact: EmergencyContact) {
        _newEmergencyContact.update { emergencyContact }
    }

    fun addNewEmergencyContact(userId: Long) {
        viewModelScope.launch {
            try {
                emergencyContactRepository.addEmergencyContact(
                    userId = userId,
                    emergencyContact = newEmergencyContact.value
                )
            } catch (e: HttpException) {
                e.printStackTrace()
            } catch (e: HttpException) {
                e.printStackTrace()
            }
        }
    }

}