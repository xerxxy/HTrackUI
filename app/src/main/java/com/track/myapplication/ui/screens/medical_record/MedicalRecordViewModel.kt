package com.track.myapplication.ui.screens.medical_record

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.track.myapplication.data.remote.model.medical_package.MedicalRecord
import com.track.myapplication.data.repository.MedicalRecordRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

enum class MedicalRecordUiState {
    SUCCESS,
    ERROR,
    LOADING
}

/*
sealed interface MedicalRecordUiState {
    object Success: MedicalRecordUiState
    object Error: MedicalRecordUiState
    object Loading: MedicalRecordUiState
}*/
class MedicalRecordViewModel(
    private val medicalRecordRepository: MedicalRecordRepository
): ViewModel() {


    private val _medicalRecordList = MutableStateFlow<List<MedicalRecord>>(emptyList())
    val medicalRecord: StateFlow<List<MedicalRecord>> = _medicalRecordList.asStateFlow()

    private val _medicalRecordUiState = MutableStateFlow<MedicalRecordUiState>(MedicalRecordUiState.LOADING)
    val medicalRecordUiState: StateFlow<MedicalRecordUiState> = _medicalRecordUiState.asStateFlow()

    fun getMedicalRecordList(userId:Long) {
        viewModelScope.launch {
            _medicalRecordUiState.value = try {
                _medicalRecordList.value = medicalRecordRepository.getMedicalRecordList(userId = userId)
                MedicalRecordUiState.SUCCESS
            } catch (e: HttpException) {
                e.printStackTrace()
                MedicalRecordUiState.ERROR
            } catch (e: IOException) {
                e.printStackTrace()
                MedicalRecordUiState.ERROR
            }
        }
    }


}