package com.htrackk.myapplication.ui.screens.registration

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.htrackk.myapplication.data.remote.model.UserRegistration
import com.htrackk.myapplication.ui.AppViewModelProvider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(
    viewModel: RegistrationViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = AppViewModelProvider.Factory),
    onRegistrationSuccessful: () -> Unit
) {

    val user by viewModel.user.collectAsState()

    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        Scaffold(
            topBar = {TopAppBar(
                title = {
                    Text(text = "Create a new account")
                },
                modifier = Modifier
                    .fillMaxWidth()
            )
            }
        ) {PaddingValues ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                //if (viewModel.registrationUiState is RegistrationUiState.Success) {
                  //  onRegistrationSuccessful()
                //}

                InputForm(
                    user = user,
                    onValueChange = viewModel::updateUser,
                    modifier = Modifier
                        .padding(paddingValues = PaddingValues)
                )

                Button(
                    onClick = {
                    viewModel.signUp()
                },
                    modifier = Modifier
                        .padding(top = 20.dp)
                ) {
                    Text(
                        text = "REGISTER",
                        fontSize = 20.sp,
                    )
                }

                when (viewModel.registrationUiState) {
                    is RegistrationUiState.Loading -> {

                    }

                    is RegistrationUiState.Error -> {
                        Text(
                            text = "Registration failed please fill the form with correct credentials!",
                            color = Color.Red,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    is RegistrationUiState.Success -> {
                        Text(
                            text = "Successful registration",
                            color = Color.Green,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

            }
        }

    }
}

@Composable
fun InputForm(
    user: UserRegistration,
    onValueChange: (UserRegistration) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(

    ) {
        OutlinedTextField(
            value = user.username,
            onValueChange = {
                onValueChange(user.copy(username = it))
            },
            label = {
                Text(text = "Enter username")
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = true,
            singleLine = true
        )
        OutlinedTextField(
            value = user.username,
            onValueChange = {
                onValueChange(user.copy(username = it))
            },
            label = {
                Text(text = "Enter username")
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = true,
            singleLine = true
        )
        OutlinedTextField(
            value = user.password,
            onValueChange = {
                onValueChange(user.copy(password = it))
            },
            label = {
                Text(text = "Enter password")
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = true,
            singleLine = true
        )
        OutlinedTextField(
            value = user.email,
            onValueChange = {
                onValueChange(user.copy(email = it))
            },
            label = {
                Text(text = "Enter email")
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = true,
            singleLine = true
        )
        OutlinedTextField(
            value = user.firstName,
            onValueChange = {
                onValueChange(user.copy(firstName = it))
            },
            label = {
                Text(text = "Enter first name")
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = true,
            singleLine = true
        )
        OutlinedTextField(
            value = user.lastName,
            onValueChange = {
                onValueChange(user.copy(lastName = it))
            },
            label = {
                Text(text = "Enter last name")
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = true,
            singleLine = true
        )
        OutlinedTextField(
            value = user.gender,
            onValueChange = {
                onValueChange(user.copy(gender = it))
            },
            label = {
                Text(text = "Enter gender")
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = true,
            singleLine = true
        )
        OutlinedTextField(
            value = user.dob,
            onValueChange = {
                onValueChange(user.copy(dob = it))
            },
            label = {
                Text(text = "Enter date of birth")
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = true,
            singleLine = true
        )
        OutlinedTextField(
            value = user.phoneNumber,
            onValueChange = {
                onValueChange(user.copy(phoneNumber = it))
            },
            label = {
                Text(text = "Enter phone number")
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = true,
            singleLine = true
        )
        OutlinedTextField(
            value = user.ethnicity,
            onValueChange = {
                onValueChange(user.copy(ethnicity = it))
            },
            label = {
                Text(text = "Enter ethnicity")
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = true,
            singleLine = true
        )
    }
}

@Preview
@Composable
fun previewRegistrationScreen() {
}