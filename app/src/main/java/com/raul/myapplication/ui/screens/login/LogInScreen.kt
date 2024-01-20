package com.raul.myapplication.ui.screens.login

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import com.raul.myapplication.R
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.raul.myapplication.data.local.LogInRoutes
import com.raul.myapplication.data.remote.model.User
import com.raul.myapplication.data.remote.model.UserLogIn
import com.raul.myapplication.ui.AppViewModelProvider
import com.raul.myapplication.ui.screens.navigation.NavigationScreen
import com.raul.myapplication.ui.screens.registration.RegistrationScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Composable
fun LogInScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: LogInViewModel = viewModel(factory = AppViewModelProvider.Factory),
    modifier: Modifier = Modifier
) {
    val user by viewModel.user.collectAsState()

    Box(
        modifier = modifier
            .fillMaxSize(1.0f)
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()

        ) {
            NavHost(
                navController = navController,
                startDestination = LogInRoutes.Login.name,
            ) {

                composable(route = LogInRoutes.Login.name) {
                    LogInInterface(
                        viewModel = viewModel,
                        onSignUpClicked = {
                            navController.navigate(LogInRoutes.Registration.name)
                        },
                        onLogInSuccessful = {
                            runBlocking(Dispatchers.Main) {
                                navController.navigate(LogInRoutes.Chartscreen.name)
                            }
                        },
                        modifier = modifier
                    )
                }

                composable(route = LogInRoutes.Registration.name) {
                    RegistrationScreen(
                        onRegistrationSuccessful = {
                            runBlocking(Dispatchers.Main) {
                                navController.navigate(LogInRoutes.Login.name)
                            }
                        }
                    )
                }

                composable(route = LogInRoutes.Chartscreen.name) {
                    NavigationScreen(
                        user = user
                    )
                }
            }
        }
    }

}

@Composable
private fun LogInInterface(
    viewModel: LogInViewModel,
    onSignUpClicked: () -> Unit,
    onLogInSuccessful: () -> Unit,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement
            .spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        UpperTextLogIn(
            modifier = modifier
        )
        LogInInputForm(
            user = viewModel.currentUser,
            onValueChange = viewModel::updateUser
        )
        Button(
            modifier = modifier
                .fillMaxWidth(),
            onClick = {
                viewModel.viewModelScope.launch(Dispatchers.IO) {

                    viewModel.logIn(viewModel.currentUser)

                    viewModel.loginRequestStatus.join()

                    if (viewModel.logInUiState is LogInUiState.Success) {
                        viewModel.userInDatabase()

                        if (viewModel.isDbPopulated) {
                            val user: User = (viewModel.logInUiState as LogInUiState.Success).user
                            viewModel.insertUserInDb(user)
                        } else {
                            viewModel.deleteUserFromDb()
                            val user: User = (viewModel.logInUiState as LogInUiState.Success).user
                            viewModel.insertUserInDb(user)
                        }


                    }
                    if (viewModel.logInUiState is LogInUiState.Success)
                        onLogInSuccessful()
                }
            }

        ) {
            Text(
                text = stringResource(id = R.string.log_in)
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Checkbox(
                checked = viewModel.checkedState,
                onCheckedChange = {
                    viewModel.updateCheckedState(it)
                }
            )
            Text(
                text = "Stay logged in"
            )
        }

        ClickableText(
            text = AnnotatedString(stringResource(id = R.string.register_text)),
            onClick = {
                onSignUpClicked()
            },
            style = TextStyle(
                textDecoration = TextDecoration.Underline,
                fontSize = 16.sp
            )


        )

        when (viewModel.logInUiState) {
            is LogInUiState.Success -> Text(
                text = "Success"
            )

            is LogInUiState.Error -> Text(
                text = "Username or password are incorrect",
                color = Color.Red,
                fontWeight = FontWeight.Bold
            )

            is LogInUiState.Loading -> Text(text = "Loading")
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogInInputForm(
    user: UserLogIn = UserLogIn("", ""),
    modifier: Modifier = Modifier,
    onValueChange: (UserLogIn) -> Unit = {}
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = user.username,
            onValueChange = {
                onValueChange(user.copy(username = it))
            },
            label = {
                Text(text = stringResource(id = R.string.enter_username))
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
                Text(text = stringResource(id = R.string.enter_password))
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = true,
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)

        )
    }

}

@Composable
fun UpperTextLogIn(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            painter = painterResource(id = R.drawable.icon_htrack),
            contentDescription = "Icon"
        )
        Text(
            text = stringResource(id = R.string.app_name),
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            modifier = modifier
                .padding(start = 8.dp)
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun previewLogInScreen() {
    LogInScreen()
}
