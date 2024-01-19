package com.raul.myapplication.data.repository

import com.raul.myapplication.data.remote.model.EmergencyContact
import com.raul.myapplication.data.remote.network.EmergencyContactApiService

interface EmergencyContactRepository {

    suspend fun getEmergencyContacts(userId: Long): List<EmergencyContact>

    suspend fun deleteEmergencyContact(userId: Long, contactId: Long)

    suspend fun addEmergencyContact(userId: Long, emergencyContact: EmergencyContact)
}

class NetworkEmergencyContactRepository(
    private val emergencyContactApiService: EmergencyContactApiService
): EmergencyContactRepository {
    override suspend fun getEmergencyContacts(userId: Long): List<EmergencyContact> {
        return emergencyContactApiService.getEmergencyContacts(userId)
    }

    override suspend fun deleteEmergencyContact(userId: Long, contactId: Long) {
        emergencyContactApiService.deleteEmergencyContact(userId, contactId)
    }

    override suspend fun addEmergencyContact(userId: Long, emergencyContact: EmergencyContact) {
        emergencyContactApiService.addEmergencyContact(userId, emergencyContact)
    }
}