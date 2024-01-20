package com.htrackk.myapplication.data

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.htrackk.myapplication.data.local.OfflineUserRepository
import com.htrackk.myapplication.data.local.UserDataBase
import com.htrackk.myapplication.data.local.UserRepository
import com.htrackk.myapplication.data.remote.network.AssessmentsApiService
import com.htrackk.myapplication.data.remote.network.EmergencyContactApiService
import com.htrackk.myapplication.data.remote.network.MedicalRecordApiService
import com.htrackk.myapplication.data.remote.network.MedicationApiService
import com.htrackk.myapplication.data.remote.network.RegistrationApiService
import com.htrackk.myapplication.data.remote.network.SettingsApiService
import com.htrackk.myapplication.data.repository.AssessmentRepository
import com.htrackk.myapplication.data.repository.EmergencyContactRepository
import com.htrackk.myapplication.data.repository.LogInRepository
import com.htrackk.myapplication.data.repository.MedicalRecordRepository
import com.htrackk.myapplication.data.repository.MedicationRepository
import com.htrackk.myapplication.data.repository.NetworkAssessmentRepository
import com.htrackk.myapplication.data.repository.NetworkEmergencyContactRepository
import com.htrackk.myapplication.data.repository.NetworkLogInRepository
import com.htrackk.myapplication.data.repository.NetworkMedicalRecordRepository
import com.htrackk.myapplication.data.repository.NetworkMedicationRepository
import com.htrackk.myapplication.data.repository.NetworkRegistrationRepository
import com.htrackk.myapplication.data.repository.NetworkSettingsRepository
import com.htrackk.myapplication.data.repository.RegistrationRepository
import com.htrackk.myapplication.data.repository.SettingsRepository
import kotlinx.serialization.json.Json
import retrofit2.Retrofit
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.create


interface AppContainer {
    //REMOTE DATA
    val logInRepository: LogInRepository
    val medicationRepository: MedicationRepository
    val assessmentRepository: AssessmentRepository
    val emergencyContactRepository: EmergencyContactRepository
    val medicalRecordRepository: MedicalRecordRepository
    val settingsRepository: SettingsRepository
    val registrationRepository: RegistrationRepository

    //LOCAL DATA
    val userRepository: UserRepository

}

class DefaultAppContainer(
    private val context: Context
) : AppContainer {

    private val baseUrl = "http://192.168.0.168:8080/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(
            Json {
                ignoreUnknownKeys = true
            }
                .asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    //REMOTE DATA

    //THIS IS FOR LOG IN
    private val retrofitLogInService: RegistrationApiService by lazy {
        retrofit.create(RegistrationApiService::class.java)
    }

    override val logInRepository: LogInRepository by lazy {
        NetworkLogInRepository(retrofitLogInService)
    }

    //THIS IS FOR GETTING THE MEDICATION LIST
    private val retrofitMedicationService: MedicationApiService by lazy {
        retrofit.create(MedicationApiService::class.java)
    }

    override val medicationRepository: MedicationRepository by lazy {
        NetworkMedicationRepository(retrofitMedicationService)
    }

    //THIS IS FOR GETTING THE ASSESSMENTS LIST
    private val retrofitAssessmentsApiService: AssessmentsApiService by lazy {
        retrofit.create(AssessmentsApiService::class.java)
    }

    override val assessmentRepository: AssessmentRepository by lazy {
        NetworkAssessmentRepository(retrofitAssessmentsApiService)
    }

    //THIS IS FOR GETTING THE EMERGENCY CONTACT LIST
    private val retrofitEmergencyContactApiService: EmergencyContactApiService by lazy {
        retrofit.create(EmergencyContactApiService::class.java)
    }

    override val emergencyContactRepository: EmergencyContactRepository by lazy {
        NetworkEmergencyContactRepository(retrofitEmergencyContactApiService)
    }

    //THIS IS FOR GETTING THE MEDICAL RECORD LIST
    private val retrofitMedicalRecordApiService: MedicalRecordApiService by lazy {
        retrofit.create(MedicalRecordApiService::class.java)
    }

    override val medicalRecordRepository: MedicalRecordRepository by lazy {
        NetworkMedicalRecordRepository(retrofitMedicalRecordApiService)
    }

    //THIS IS FOR GETTING THE SETTINGS
    private val retrofitSettingsApiService: SettingsApiService by lazy {
        retrofit.create(SettingsApiService::class.java)
    }

    override val settingsRepository: SettingsRepository by lazy {
        NetworkSettingsRepository(retrofitSettingsApiService)
    }

    //THIS IS FOR REGISTRATION
    private val retrofitRegistrationApiService: RegistrationApiService by lazy {
        retrofit.create(RegistrationApiService::class.java)
    }

    override val registrationRepository: RegistrationRepository by lazy {
        NetworkRegistrationRepository(retrofitRegistrationApiService)
    }

    //LOCAL DATA
    override val userRepository: UserRepository by lazy {
        OfflineUserRepository(UserDataBase.getDatabase(context).userDao())
    }


}

