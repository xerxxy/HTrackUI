package com.track.myapplication.ui.screens.login


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.track.myapplication.data.local.UserRepository
import com.track.myapplication.data.mapper.toUserEntity
import com.track.myapplication.data.remote.model.Medication
import com.track.myapplication.data.remote.model.User
import com.track.myapplication.data.remote.model.UserLogIn
import com.track.myapplication.data.repository.LogInRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


sealed interface LogInUiState {
    data class Success(val user: User) : LogInUiState
    object Error : LogInUiState
    object Loading : LogInUiState
}

class LogInViewModel(
    private val logInRepository: LogInRepository,
    private val offlineUserRepository: UserRepository
) : ViewModel() {

    var logInUiState: LogInUiState by mutableStateOf(LogInUiState.Loading)
        private set

    private val _user = MutableStateFlow(User(0, "", "", "", "", "", "", "", "", ""))
    val user: StateFlow<User> = _user.asStateFlow()

    private val _medicationList = MutableStateFlow(emptyList<Medication>())
    val medicationList: StateFlow<List<Medication>> = _medicationList.asStateFlow()

    var currentUser: UserLogIn by mutableStateOf(UserLogIn("", ""))
        private set

    var checkedState: Boolean by mutableStateOf(false)
        private set

    var isDbPopulated: Boolean by mutableStateOf(false)
        private set

    lateinit var loginRequestStatus: Job
        private set

    fun logIn(user: UserLogIn) {
        loginRequestStatus = viewModelScope.launch {
            logInUiState = LogInUiState.Loading
            logInUiState = try {
                val response = logInRepository.logIn(user = user)
                _user.value = response
                LogInUiState.Success(response)
            } catch (e: HttpException) {
                e.printStackTrace()
                LogInUiState.Error
            } catch (e: IOException) {
                e.printStackTrace()
                LogInUiState.Error
            }
        }
    }

    fun updateUser(user: UserLogIn) {
        currentUser = user
    }

    fun updateCheckedState(state: Boolean) {
        checkedState = state
    }

     fun userInDatabase(): Job {
        return viewModelScope.launch(Dispatchers.IO) {
            isDbPopulated = offlineUserRepository.getUserId() != null
        }
    }

    fun insertUserInDb(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            offlineUserRepository.insert(user.toUserEntity())
        }
    }

    fun deleteUserFromDb() {
        viewModelScope.launch(Dispatchers.IO) {
            offlineUserRepository.delete()
        }
    }

}
