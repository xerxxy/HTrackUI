package com.raul.myapplication.ui

import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.raul.myapplication.HealthMonitoringApplication
import com.raul.myapplication.ui.screens.login.LogInViewModel
import com.raul.myapplication.ui.screens.medication.MedicationViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import com.raul.myapplication.ui.screens.assesments.AssessmentsViewModel
import com.raul.myapplication.ui.screens.emergency_contact.EmergencyContactViewModel
import com.raul.myapplication.ui.screens.medical_record.MedicalRecordViewModel
import com.raul.myapplication.ui.screens.settings.SettingsViewModel


object AppViewModelProvider {

    val Factory = viewModelFactory {
        //Initializer foe LogInViewModel
        initializer {
            LogInViewModel(
                healthMonitoringApplication().container.logInRepository,
                healthMonitoringApplication().container.userRepository
            )
        }

        //Initializer for AssessmentViewModel
        initializer {
            AssessmentsViewModel(
                healthMonitoringApplication().container.assessmentRepository,
                healthMonitoringApplication().container.userRepository
            )
        }

        //Initializer for MedicationViewModel
        initializer {
            MedicationViewModel(
                healthMonitoringApplication().container.medicationRepository,
                healthMonitoringApplication().container.userRepository
            )
        }

        //Initializer for EmergencyContactViewModel
        initializer {
            EmergencyContactViewModel(
                healthMonitoringApplication().container.emergencyContactRepository,
                healthMonitoringApplication().container.userRepository
            )
        }

        //Initializer for MedicalRecordViewModel
        initializer {
            MedicalRecordViewModel(
                healthMonitoringApplication().container.medicalRecordRepository
            )
        }

        //Initializer for SettingsViewModel
        initializer {
            SettingsViewModel(
                healthMonitoringApplication().container.settingsRepository
            )
        }

    }

}

fun CreationExtras.healthMonitoringApplication() : HealthMonitoringApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as HealthMonitoringApplication)