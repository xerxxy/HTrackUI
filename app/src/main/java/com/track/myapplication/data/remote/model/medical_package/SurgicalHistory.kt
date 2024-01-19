package com.track.myapplication.data.remote.model.medical_package

import kotlinx.serialization.SerialName

@Serializable
data class SurgicalHistory(
    val id: Long,
    @SerialName(value = "record_id")
    val recordId: Long,
    val surgery: String,
)
