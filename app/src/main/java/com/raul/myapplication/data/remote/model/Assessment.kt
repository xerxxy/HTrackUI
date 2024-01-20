package com.raul.myapplication.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class Assessment (
    @SerialName(value = "ass_id")
    val assessmentId: Int,
    val assessment: String
)