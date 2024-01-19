package com.raul.myapplication.data.repository

import com.raul.myapplication.data.remote.model.Medication
import com.raul.myapplication.data.remote.model.MedicationBody
import com.raul.myapplication.data.remote.model.User
import com.raul.myapplication.data.remote.network.MedicationApiService

interface MedicationRepository {

    suspend fun getMedicationList(id: Long): List<Medication>
    suspend fun deleteMedication(userId: Long, medicationId: Long)
    suspend fun addMedication(userId: Long, medication: MedicationBody)
}

class NetworkMedicationRepository(
    private val medicationApiService: MedicationApiService
) : MedicationRepository {

    override suspend fun getMedicationList(id: Long): List<Medication> {
        return medicationApiService.getMedication(id = id)
    }

    override suspend fun deleteMedication(userId: Long, medicationId: Long) {
        medicationApiService.deleteMedication(userId = userId, medicationId = medicationId)
    }

    override suspend fun addMedication(userId: Long, medication: MedicationBody) {
        return medicationApiService.addMedication(userId, medication = medication)
    }
}