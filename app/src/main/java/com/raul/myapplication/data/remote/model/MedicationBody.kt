package com.raul.myapplication.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MedicationBody (
    @SerialName(value = "drug_name")
    val drugName: String,
    val duration: Int
)