package com.track.myapplication.ui.screens.emergency_contact

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.track.myapplication.data.remote.model.EmergencyContact
import com.track.myapplication.data.remote.model.User
import com.track.myapplication.ui.AppViewModelProvider
import com.track.myapplication.ui.components.DismissBackground
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay


@Composable
fun EmergencyContactScreen(
    viewModel: EmergencyContactViewModel = viewModel(factory = AppViewModelProvider.Factory),
    user: User
) {

    val isLoading by viewModel.isLoading.collectAsState()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isLoading)

    viewModel.getEmergencyContactList(userId = user.id)
    val emergencyContactList by viewModel.emergencyContactList.collectAsState()

    var newEmergencyContactScreenVisibility by remember {
        mutableStateOf(false)
    }

    val newEmergencyContact by viewModel.newEmergencyContact.collectAsState()


    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = {
            viewModel.getEmergencyContactList(userId = user.id)
        }) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box() {
                when (viewModel.emergencyContactUiState) {
                    is EmergencyContactUiState.Success -> {
                        LazyColumn {
                            items(emergencyContactList) { item ->
                                ContactItem(
                                    contact = item,
                                    onRemove = {
                                        viewModel.deleteEmergencyContact(
                                            userId = user.id,
                                            emergencyContact = item
                                        )
                                    }
                                )
                            }
                        }
                    }

                    is EmergencyContactUiState.Error -> {
                        Text(text = "ERROR")
                    }

                    is EmergencyContactUiState.Loading -> {
                        Text(text = "LOADING")
                    }
                }
                if (newEmergencyContactScreenVisibility) {
                    NewEmergencyContactScreen(
                        contact = newEmergencyContact,
                        onValueChange = viewModel::updateNewContact,
                        onSaveButton = {
                            viewModel.addNewEmergencyContact(user.id)
                            newEmergencyContactScreenVisibility = false
                            viewModel.getEmergencyContactList(user.id)
                            viewModel.updateNewContact(EmergencyContact("", "", 0))
                        }
                    )
                }

                FloatingActionButton(
                    onClick = {
                        newEmergencyContactScreenVisibility = !newEmergencyContactScreenVisibility
                    },
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(bottom = 16.dp, end = 16.dp)
                ) {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = "Add new emergency contact")
                }
            }
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactItem(
    contact: EmergencyContact = EmergencyContact(
        name = "Raul",
        phoneNumber = "+40123898001",
        id = 0
    ),
    onRemove: (EmergencyContact) -> Unit
) {
    val context = LocalContext.current
    var show by remember { mutableStateOf(true) }
    val currentItem by rememberUpdatedState(newValue = contact)
    val dismissState = rememberDismissState(
        confirmValueChange = {
            if (it == DismissValue.DismissedToEnd) {
                show = false
                true
            } else false
        },
        positionalThreshold = { 150.dp.toPx() }
    )
    AnimatedVisibility(
        visible = show,
        exit = fadeOut(spring())
    ) {
        SwipeToDismiss(
            state = dismissState,
            background = {
                DismissBackground(dismissState = dismissState)
            },
            dismissContent = {
                Card(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, bottom = 4.dp, top = 4.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier
                            .background(Color.LightGray)
                            .padding(24.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = contact.name,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(bottom = 16.dp)
                        )
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Icon(
                                imageVector = Icons.Default.Phone,
                                contentDescription = "Phone number",
                                modifier = Modifier
                                    .padding(end = 16.dp))
                            Text(
                                text = contact.phoneNumber,
                                fontSize = 24.sp
                            )
                        }
                    }
                }
            }
        )
    }
    LaunchedEffect(key1 = show, Dispatchers.Main) {
        if (!show) {
            delay(800L)
            onRemove(currentItem)
            Toast.makeText(context, "Contact removed", Toast.LENGTH_LONG).show()
        }
    }

}


@Composable
fun NewEmergencyContactScreen(
    contact: EmergencyContact = EmergencyContact("", "", 0),
    modifier: Modifier = Modifier,
    onValueChange: (EmergencyContact) -> Unit,
    onSaveButton: () -> Unit
) {
    Surface(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .border(4.dp, color = Color.Black, shape = RoundedCornerShape(4.dp))

    ) {
        Column(
            modifier = modifier
                .padding(6.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = contact.name,
                onValueChange = {
                    onValueChange(contact.copy(name = it))
                },
                label = {
                    Text(text = "Enter name ")
                },
                modifier = modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                enabled = true,
                singleLine = true
            )
            OutlinedTextField(
                value = contact.phoneNumber,
                onValueChange = {
                    onValueChange(contact.copy(phoneNumber = it))
                },
                label = {
                    Text(text = "Enter phone number")
                },
                modifier = modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                enabled = true,
                singleLine = true
            )
            Button(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                onClick = { onSaveButton() }
            ) {
                Text(text = "Add emergency contact")
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewContactItem() {
    Column {
        ContactItem(onRemove = {})
        ContactItem(onRemove = {})
        ContactItem(onRemove = {})
        ContactItem(onRemove = {})
        ContactItem(onRemove = {})
        ContactItem(onRemove = {})
        ContactItem(onRemove = {})
        ContactItem(onRemove = {})
        ContactItem(onRemove = {})
        ContactItem(onRemove = {})
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewNewMedicationScreen() {
    NewEmergencyContactScreen(onValueChange = {}) {
    }
}