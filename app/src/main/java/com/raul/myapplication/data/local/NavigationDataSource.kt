package com.raul.myapplication.data.local

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.res.painterResource
import com.raul.myapplication.R
import com.raul.myapplication.ui.screens.navigation.NavigationItem


// THIS IS FOR NAVIGATION COMPONENT IT IS NOT RELATED TO ROOM OR ANY OTHER DATABASE OR TECHNIQUE FOR PERSISTING DATA
object NavigationDataSource {

    val navigationItems = listOf<NavigationItem>(
        NavigationItem(
            title = "Biometrics",
            selectedIcon = R.drawable.home_icon,
            route = "BiometricsScreen"
        ),
        NavigationItem(
            title = "Medical Record",
            selectedIcon = R.drawable.medical_record_icon,
            route = "MedRecordScreen"
        ),
        NavigationItem(
            title = "Medications",
            selectedIcon = R.drawable.medications_icon,
            route = "MedicationsScreen"
        ),
        NavigationItem(
            title = "Assessments",
            selectedIcon = R.drawable.assesements_icon,
            route = "AssessmentsScreen"
        ),
        NavigationItem(
            title = "Settings",
            selectedIcon = R.drawable.settings_icon,
            route = "SettingsScreen"
        ),
        NavigationItem(
            title = "Emergency contacts!",
            selectedIcon = R.drawable.baseline_contact_emergency_24,
            route = "EmergencyContactScreen"
        )
    )
}

enum class LogInRoutes() {
    Login,
    Registration,
    Chartscreen
}