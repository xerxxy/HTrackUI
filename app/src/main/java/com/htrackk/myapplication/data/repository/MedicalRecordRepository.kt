package com.htrackk.myapplication.data.repository

import com.htrackk.myapplication.data.remote.model.medical_package.MedicalRecord
import com.htrackk.myapplication.data.remote.network.MedicalRecordApiService


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