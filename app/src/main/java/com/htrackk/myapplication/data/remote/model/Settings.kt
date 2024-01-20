package com.htrackk.myapplication.data.remote.model

import kotlinx.serialization.SerialName

@Serializable
data class Settings(
    val threshold: Int,
    @SerialName(value = "notifLevel")
    val notificationLevel: Int
)