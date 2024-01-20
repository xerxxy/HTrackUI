package com.raul.myapplication.data.remote.network

import com.raul.myapplication.data.remote.model.EmergencyContact
import com.raul.myapplication.data.remote.model.MedicationBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface EmergencyContactApiService {

    @GET("users/{user_id}/contacts")
    suspend fun getEmergencyContacts(@Path(value = "user_id") userId: Long): List<EmergencyContact>

    @DELETE("users/{user_id}/contacts/{contact_id}")
    suspend fun deleteEmergencyContact(
        @Path(value = "user_id") userId: Long,
        @Path(value = "contact_id") contactId: Long
    )

    @POST("users/{user_id}/contacts")
    suspend fun addEmergencyContact(
        @Path(value = "user_id") userId: Long,
        @Body emergencyContact: EmergencyContact
    )
}