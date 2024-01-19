package com.track.myapplication.data.repository

import com.track.myapplication.data.remote.model.Assessment
import com.track.myapplication.data.remote.network.AssessmentsApiService

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