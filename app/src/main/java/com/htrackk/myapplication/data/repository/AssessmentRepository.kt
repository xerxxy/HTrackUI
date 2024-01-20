package com.htrackk.myapplication.data.repository

import com.htrackk.myapplication.data.remote.model.Assessment
import com.htrackk.myapplication.data.remote.network.AssessmentsApiService

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