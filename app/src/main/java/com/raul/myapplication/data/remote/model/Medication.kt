package com.raul.myapplication.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Medication(

    @SerialName(value = "id")
    val medicationId: Long,
    @SerialName(value = "drug_name")
    val drugName: String,
    val duration: Int
)