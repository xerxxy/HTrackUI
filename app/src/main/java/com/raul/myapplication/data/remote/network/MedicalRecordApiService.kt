package com.raul.myapplication.data.remote.network

import com.raul.myapplication.data.remote.model.medical_package.MedicalRecord
import retrofit2.http.GET
import retrofit2.http.Path

interface MedicalRecordApiService {

    @GET("/users/{id}/record")
    suspend fun getMedicalRecordList(@Path(value = "id") id: Long): List<MedicalRecord>

}