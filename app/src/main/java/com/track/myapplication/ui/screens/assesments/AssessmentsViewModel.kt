package com.track.myapplication.ui.screens.assesments

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.track.myapplication.data.local.UserRepository
import com.track.myapplication.data.remote.model.Assessment
import com.track.myapplication.data.repository.AssessmentRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface AssessmentUiState {
    data class Success(val assessments: List<Assessment>) : AssessmentUiState

    object Error : AssessmentUiState

    object Loading : AssessmentUiState
}


class AssessmentsViewModel(
    private val assessmentRepository: AssessmentRepository,
    private val offlineUserRepository: UserRepository
) : ViewModel() {

    var assessmentUiState: AssessmentUiState by mutableStateOf(AssessmentUiState.Loading)
        private set

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()
    fun getAssessmentList(userId: Long) {
        viewModelScope.launch {
            _isLoading.value = true
            assessmentUiState = try {
                val response = assessmentRepository.getAssessments(id = userId)
                AssessmentUiState.Success(response)
            }catch (e: IOException) {
                e.printStackTrace()
                AssessmentUiState.Error
            } catch (e: HttpException) {
                e.printStackTrace()
                AssessmentUiState.Error
            }
            _isLoading.value = false
        }
    }
}
