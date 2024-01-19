package com.track.myapplication.ui.screens.medication

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.track.myapplication.data.local.UserRepository
import com.track.myapplication.data.remote.model.Medication
import com.track.myapplication.data.remote.model.MedicationBody
import com.track.myapplication.data.repository.MedicationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface MedicationUiState {
    data class Success(val medication: MutableList<Medication>) : MedicationUiState

    object Error : MedicationUiState

    object Loading : MedicationUiState
}


class MedicationViewModel(
    private val medicationRepository: MedicationRepository,
    private val offlineUserRepository: UserRepository
) : ViewModel() {

    var medicationUiState: MedicationUiState by mutableStateOf(MedicationUiState.Loading)
        private set

    private val _medicationList = MutableStateFlow(emptyList<Medication>())
    val medicationList: StateFlow<List<Medication>> = _medicationList.asStateFlow()
    var userId: Long? by mutableStateOf(1)
        private set

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _newMedication = MutableStateFlow(MedicationBody("", 0))
    val newMedication = _newMedication.asStateFlow()

    var messageForNewMedication: String by mutableStateOf("")
        private set
    fun getMedicationList(userId: Long) {
        viewModelScope.launch(Dispatchers.Main) {
            medicationUiState = try {
                _isLoading.value = true
                val response = medicationRepository.getMedicationList(id = userId ?: 1)
                _medicationList.value = response
                MedicationUiState.Success(response.toMutableList())
            } catch (e: HttpException) {
                e.printStackTrace()
                MedicationUiState.Error
            } catch (e: IOException) {
                e.printStackTrace()
                MedicationUiState.Error
            }
            _isLoading.value = false
        }
    }

    fun deleteMedication(userId: Long, medication: Medication) {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                medicationRepository.deleteMedication(
                    userId = userId,
                    medicationId = medication.medicationId
                )
            } catch (e: HttpException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            _medicationList.update {
                val mutableList = it.toMutableList()
                mutableList.remove(medication)
                mutableList
            }
        }
        Log.d("DEBUG LIST", "${medication.drugName} deleted")
    }

    suspend fun refresh() {
        _medicationList.update {
            var mutableList = it.toMutableList()
            val job = viewModelScope.launch {
                mutableList = try {
                    medicationRepository.getMedicationList(userId ?: 1).toMutableList()
                } catch (e: IOException) {
                    e.printStackTrace()
                    emptyList<Medication>().toMutableList()
                } catch (e: HttpException) {
                    e.printStackTrace()
                    emptyList<Medication>().toMutableList()
                }
            }
            job.join()
            mutableList
        }
    }

    fun updateNewMedication(medication: MedicationBody) {
        _newMedication.update { medication }
    }

    fun addNewMedication(userId: Long) {
        viewModelScope.launch {
             try {
                medicationRepository.addMedication(
                    userId = userId,
                    medication = newMedication.value
                )
            } catch (e: HttpException) {
                e.printStackTrace()
            } catch (e: HttpException) {
                e.printStackTrace()
            }
        }
    }



}