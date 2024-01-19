package com.raul.myapplication.data.remote.network

import com.raul.myapplication.data.remote.model.Assessment
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface AssessmentsApiService {

    @GET("/users/{id}/assessments")
    suspend fun getAssessments(@Path(value = "id") id: Long): List<Assessment>
}