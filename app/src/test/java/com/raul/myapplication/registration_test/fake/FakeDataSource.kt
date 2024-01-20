package com.raul.myapplication.registration_test.fake

import com.raul.myapplication.data.remote.model.User
import com.raul.myapplication.data.remote.model.UserLogIn
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

object FakeDataSource {

    private const val id: String = "1122"
    private const val dob: String = "2003-07-21"
    private const val email: String = "ceva@gmail.com"
    private const val ethnicity: String = "white"
    private const val firstName: String = "Raul"
    private const val gender: String = "M"
    private const val lastName: String = "Spatariu"
    private const val password: String = "parola"
    private const val phoneNumber: String = "0421412"
    private const val username: String = "raul"

    val userLogin: UserLogIn = UserLogIn(username, password)
    val user: User = User(id, dob, email, ethnicity, firstName, gender, lastName, password, phoneNumber, username)


}