package com.raul.myapplication.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class EmergencyContact(
    val name: String,
    @SerialName(value = "phone_nr")
    val phoneNumber: String,
    val id: Long
)
