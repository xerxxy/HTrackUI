package com.track.myapplication.data.remote.model

import kotlinx.serialization.SerialName

@Serializable
data class Medication(

    @SerialName(value = "id")
    val medicationId: Long,
    @SerialName(value = "drug_name")
    val drugName: String,
    val duration: Int
)