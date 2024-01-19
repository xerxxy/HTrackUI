package com.track.myapplication.data.remote.model.medical_package

import kotlinx.serialization.SerialName

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
