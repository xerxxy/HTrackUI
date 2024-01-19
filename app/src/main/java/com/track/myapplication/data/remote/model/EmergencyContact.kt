package com.track.myapplication.data.remote.model

import kotlinx.serialization.SerialName


@Serializable
data class EmergencyContact(
    val name: String,
    @SerialName(value = "phone_nr")
    val phoneNumber: String,
    val id: Long
)
