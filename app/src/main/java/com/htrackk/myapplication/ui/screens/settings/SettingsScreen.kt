package com.htrackk.myapplication.ui.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.htrackk.myapplication.data.remote.model.User
import com.htrackk.myapplication.ui.AppViewModelProvider

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = viewModel(factory = AppViewModelProvider.Factory),
    user: User
) {

    val settings by viewModel.settings.collectAsState()
    viewModel.getSettings(userId = user.id)

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .padding(24.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .padding(12.dp)
                ) {
                    Text(
                        text = "Threshold:",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = settings.threshold.toString(),
                        fontSize = 20.sp
                    )
                }
            }
            Card(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .padding(12.dp)
                ) {
                    Text(
                        text = "Notification level:",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = settings.notificationLevel.toString(),
                        fontSize = 20.sp
                    )
                }
            }
            
            when (viewModel.settingsUiState) {
                is SettingsUiState.Loading -> {
                    
                }
                is SettingsUiState.Success -> {
                    
                }
                is SettingsUiState.Error -> {
                    Text(text = "ERROR")
                }
            }
        }

    }


}