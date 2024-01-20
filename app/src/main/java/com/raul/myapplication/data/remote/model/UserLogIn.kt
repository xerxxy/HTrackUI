package com.raul.myapplication.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class UserLogIn(
    val username: String,
    val password: String
)
