package com.track.myapplication.data.remote.model

import kotlinx.serialization.SerialName

@Serializable
data class Settings(
    val threshold: Int,
    @SerialName(value = "notif_level")
    val notificationLevel: Int
)