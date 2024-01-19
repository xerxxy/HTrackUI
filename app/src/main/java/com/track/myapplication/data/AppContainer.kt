package com.track.myapplication.data

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.track.myapplication.data.local.OfflineUserRepository
import com.track.myapplication.data.local.UserDataBase
import com.track.myapplication.data.local.UserRepository
import com.track.myapplication.data.remote.network.AssessmentsApiService
import com.track.myapplication.data.remote.network.EmergencyContactApiService
import com.track.myapplication.data.remote.network.MedicalRecordApiService
import com.track.myapplication.data.remote.network.MedicationApiService
import com.track.myapplication.data.remote.network.RegistrationApiService
import com.track.myapplication.data.remote.network.SettingsApiService
import com.track.myapplication.data.repository.AssessmentRepository
import com.track.myapplication.data.repository.EmergencyContactRepository
import com.track.myapplication.data.repository.LogInRepository
import com.track.myapplication.data.repository.MedicalRecordRepository
import com.track.myapplication.data.repository.MedicationRepository
import com.track.myapplication.data.repository.NetworkAssessmentRepository
import com.track.myapplication.data.repository.NetworkEmergencyContactRepository
import com.track.myapplication.data.repository.NetworkLogInRepository
import com.track.myapplication.data.repository.NetworkMedicalRecordRepository
import com.track.myapplication.data.repository.NetworkMedicationRepository
import com.track.myapplication.data.repository.NetworkSettingsRepository
import com.track.myapplication.data.repository.SettingsRepository
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

    //THIS IS FOR GETTING THE
    private val retrofitSettingsApiService: SettingsApiService by lazy {
        retrofit.create(SettingsApiService::class.java)
    }

    override val settingsRepository: SettingsRepository by lazy {
        NetworkSettingsRepository(retrofitSettingsApiService)
    }


    //LOCAL DATA
    override val userRepository: UserRepository by lazy {
        OfflineUserRepository(UserDataBase.getDatabase(context).userDao())
    }


}

