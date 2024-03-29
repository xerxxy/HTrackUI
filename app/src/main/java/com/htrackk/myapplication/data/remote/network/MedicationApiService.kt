package com.htrackk.myapplication.data.remote.network

import com.htrackk.myapplication.data.remote.model.Medication
import com.htrackk.myapplication.data.remote.model.MedicationBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MedicationApiService {

    @GET("/users/{id}/medications")
    suspend fun getMedication(@Path(value = "id") id: Long): List<Medication>

    @DELETE("/users/{user_id}/medications/{medication_id}")
    suspend fun deleteMedication(
        @Path(value = "user_id") userId: Long,
        @Path(value = "medication_id") medicationId: Long
    )

    @POST("users/{user_id}/medications")
    suspend fun addMedication(
        @Path(value = "user_id") userId: Long,
        @Body medication: MedicationBody
    )


}