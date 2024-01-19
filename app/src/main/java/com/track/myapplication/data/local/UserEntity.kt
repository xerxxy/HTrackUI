package com.track.myapplication.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_info")
data class UserEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    @ColumnInfo(name = "date_of_birth")
    val dob: String,
    val email: String,
    val ethnicity: String,
    @ColumnInfo(name = "first_name")
    val firstName: String,
    val gender: String,
    @ColumnInfo(name = "last_name")
    val lastName: String,
    val password: String, //TODO CE FACEM CU PAROLA
    @ColumnInfo(name = "phone_nr")
    val phoneNumber: String,
    val username: String
)