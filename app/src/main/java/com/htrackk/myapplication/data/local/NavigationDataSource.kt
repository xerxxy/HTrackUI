package com.htrackk.myapplication.data.local

import com.htrackk.myapplication.R
import com.htrackk.myapplication.ui.screens.navigation.NavigationItem


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