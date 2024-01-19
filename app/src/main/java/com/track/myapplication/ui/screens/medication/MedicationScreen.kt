package com.track.myapplication.ui.screens.medication

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.track.myapplication.data.remote.model.Medication
import com.track.myapplication.data.remote.model.MedicationBody
import com.track.myapplication.data.remote.model.User
import com.track.myapplication.ui.AppViewModelProvider
import com.track.myapplication.ui.components.DismissBackground
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay


@Composable
fun MedicationScreen(
    viewModel: MedicationViewModel = viewModel(factory = AppViewModelProvider.Factory),
    user: User
) {

    val isLoading by viewModel.isLoading.collectAsState()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isLoading)

    viewModel.getMedicationList(userId = user.id)
    val medicationList by viewModel.medicationList.collectAsState()

    var newMedicationScreenVisibility by remember {
        mutableStateOf(false)
    }

    val newMedication by viewModel.newMedication.collectAsState()


    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = {
            viewModel.getMedicationList(userId = user.id)
        }) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box {
                when (viewModel.medicationUiState) {
                    is MedicationUiState.Success -> {
                        LazyColumn {
                            items(medicationList) { item ->
                                MedicationItem(
                                    medication = item,
                                    onRemove = {
                                        viewModel.deleteMedication(
                                            userId = user.id,
                                            medication = item
                                        )
                                        Log.d(
                                            "DEBUG LIST",
                                            "${item.drugName} delete function called"
                                        )
                                    }
                                )
                            }
                        }
                    }

                    is MedicationUiState.Error -> {
                        Text(text = "ERROR")
                    }

                    is MedicationUiState.Loading -> {
                        Text(text = "LOADING")
                    }
                }
                if (newMedicationScreenVisibility) {
                    NewMedicationScreen(
                        medication = newMedication,
                        onValueChange = viewModel::updateNewMedication,
                        onSaveButton = {
                            viewModel.addNewMedication(user.id)
                            newMedicationScreenVisibility = false
                            viewModel.getMedicationList(user.id)
                            viewModel.updateNewMedication(MedicationBody("", 0))
                        }
                    )
                }

                FloatingActionButton(
                    onClick = {
                        newMedicationScreenVisibility = !newMedicationScreenVisibility
                    },
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(bottom = 16.dp, end = 16.dp)
                ) {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = "Add new medication")
                }
            }
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicationItem(
    medication: Medication = Medication(
        medicationId = 2424,
        drugName = "Antibiotics",
        duration = 128
    ),
    onRemove: (Medication) -> Unit
) {
    val context = LocalContext.current
    var show by remember { mutableStateOf(true) }
    val currentItem by rememberUpdatedState(newValue = medication)
    val dismissState = rememberDismissState(
        confirmValueChange = {
            if (it == DismissValue.DismissedToEnd) {
                Log.d("DEBUG LIST", "${medication.drugName} set to not be visible anymore")
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
                        .padding(16.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(2.dp, Color.Black)
                ) {
                    Column(
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = medication.drugName,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Duration = ${medication.duration}",
                            fontSize = 16.sp
                        )
                    }
                }
            }
        )
    }
    LaunchedEffect(key1 = show, Dispatchers.Main) {
        if (!show) {
            delay(800L)
            onRemove(currentItem)
            Toast.makeText(context, "Medication removed", Toast.LENGTH_LONG).show()
        }
    }

}


@Composable
fun NewMedicationScreen(
    medication: MedicationBody = MedicationBody("", 0),
    modifier: Modifier = Modifier,
    onValueChange: (MedicationBody) -> Unit,
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
                value = medication.drugName,
                onValueChange = {
                    onValueChange(medication.copy(drugName = it))
                },
                label = {
                    Text(text = "Enter medication name")
                },
                modifier = modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                enabled = true,
                singleLine = true
            )
            OutlinedTextField(
                value = medication.duration.toString(),
                onValueChange = {
                    onValueChange(medication.copy(duration = it.toInt()))
                },
                label = {
                    Text(text = "Enter duration")
                },
                modifier = modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                enabled = true,
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )
            Button(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                onClick = { onSaveButton() }
            ) {
                Text(text = "Add medication")
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewMedicationitem() {
    MedicationItem(onRemove = {})
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewNewMedicationScreen() {
    NewMedicationScreen(onValueChange = {}) {
    }
}