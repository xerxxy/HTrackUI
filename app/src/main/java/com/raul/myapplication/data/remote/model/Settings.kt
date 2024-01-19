package com.raul.myapplication.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Settings(
    val threshold: Int,
    @SerialName(value = "notif_level")
    val notificationLevel: Int
)