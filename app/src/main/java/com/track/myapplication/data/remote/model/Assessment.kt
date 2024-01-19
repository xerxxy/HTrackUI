package com.track.myapplication.data.remote.model

import kotlinx.serialization.SerialName

@Serializable
class Assessment (
    @SerialName(value = "ass_id")
    val assessmentId: Int,
    val assessment: String
)