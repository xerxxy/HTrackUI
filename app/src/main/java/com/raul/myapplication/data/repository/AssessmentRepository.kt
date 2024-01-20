package com.raul.myapplication.data.repository

import com.raul.myapplication.data.remote.model.Assessment
import com.raul.myapplication.data.remote.model.User
import com.raul.myapplication.data.remote.model.UserLogIn
import com.raul.myapplication.data.remote.network.AssessmentsApiService
import com.raul.myapplication.data.remote.network.RegistrationApiService

interface AssessmentRepository {
    suspend fun getAssessments(id: Long): List<Assessment>
}

class NetworkAssessmentRepository(
    private val assessmentsApiService: AssessmentsApiService
) : AssessmentRepository {

    override suspend fun getAssessments(id: Long): List<Assessment> {
        return assessmentsApiService.getAssessments(id = id)
    }

}