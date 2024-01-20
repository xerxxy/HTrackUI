package com.htrackk.myapplication.data.remote.model

import kotlinx.serialization.SerialName

@Serializable
data class User(
    val id: Long,
    @SerialName(value = "date_of_birth")
    val dob: String,
    val email: String,
    @SerialName(value = "etnicity")
    val ethnicity: String,
    @SerialName(value = "first_name")
    val firstName: String,
    val gender: String,
    @SerialName(value = "last_name")
    val lastName: String,
    val password: String, // TODO(PAROLA VA TREBUI SALVATA ALTCUMVA)
    @SerialName(value = "phone_nr")
    val phoneNumber: String,
    val username: String

)

