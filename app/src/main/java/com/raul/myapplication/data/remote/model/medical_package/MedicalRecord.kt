package com.raul.myapplication.data.remote.model.medical_package

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MedicalRecord (
    @SerialName(value = "record")
    val condition: String,
    val present: Boolean,
    @SerialName(value = "id")
    val recordId: Long,
    @SerialName(value = "surgeries")
    val surgicalHistoryList: List<SurgicalHistory>
)
