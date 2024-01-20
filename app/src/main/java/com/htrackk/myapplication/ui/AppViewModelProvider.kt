package com.htrackk.myapplication.ui

import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.htrackk.myapplication.HealthMonitoringApplication
import com.htrackk.myapplication.ui.screens.login.LogInViewModel
import com.htrackk.myapplication.ui.screens.medication.MedicationViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import com.htrackk.myapplication.ui.screens.assesments.AssessmentsViewModel
import com.htrackk.myapplication.ui.screens.emergency_contact.EmergencyContactViewModel
import com.htrackk.myapplication.ui.screens.medical_record.MedicalRecordViewModel
import com.htrackk.myapplication.ui.screens.registration.RegistrationViewModel
import com.htrackk.myapplication.ui.screens.settings.SettingsViewModel


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

        //Initializer for RegistrationViewModel
        initializer {
            RegistrationViewModel(
                healthMonitoringApplication().container.registrationRepository
            )
        }

    }

}

fun CreationExtras.healthMonitoringApplication() : HealthMonitoringApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as HealthMonitoringApplication)