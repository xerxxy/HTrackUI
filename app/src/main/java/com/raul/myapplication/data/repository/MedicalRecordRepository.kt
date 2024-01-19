package com.raul.myapplication.data.repository

import com.raul.myapplication.data.remote.model.medical_package.MedicalRecord
import com.raul.myapplication.data.remote.network.MedicalRecordApiService


interface MedicalRecordRepository {
    suspend fun getMedicalRecordList(userId: Long): List<MedicalRecord>
}

class NetworkMedicalRecordRepository(
    private val medicalRecordApiService: MedicalRecordApiService
) : MedicalRecordRepository {

    override suspend fun getMedicalRecordList(userId: Long): List<MedicalRecord> {
        return medicalRecordApiService.getMedicalRecordList(id = userId)
    }

}