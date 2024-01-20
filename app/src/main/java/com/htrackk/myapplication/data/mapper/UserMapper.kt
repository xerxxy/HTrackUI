package com.htrackk.myapplication.data.mapper

import com.htrackk.myapplication.data.local.UserEntity
import com.htrackk.myapplication.data.remote.model.User

fun UserEntity.toUser(): User {
    return User(
        id = id,
        dob = dob,
        email = email,
        ethnicity = ethnicity,
        firstName = firstName,
        gender = gender,
        lastName = lastName,
        password = password,
        phoneNumber = phoneNumber,
        username = username
    )
}

fun User.toUserEntity(): UserEntity {
    return UserEntity(
        id = id,
        dob = dob,
        email = email,
        ethnicity = ethnicity,
        firstName = firstName,
        gender = gender,
        lastName = lastName,
        password = password,
        phoneNumber = phoneNumber,
        username = username
    )
}