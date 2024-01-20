package com.htrackk.myapplication.data.remote.model

import kotlinx.serialization.SerialName

@Serializable
data class MedicationBody (
    @SerialName(value = "drug_name")
    val drugName: String,
    val duration: Int
)