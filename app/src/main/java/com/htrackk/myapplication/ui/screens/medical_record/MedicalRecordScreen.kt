package com.htrackk.myapplication.ui.screens.medical_record

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.htrackk.myapplication.data.remote.model.User
import com.htrackk.myapplication.data.remote.model.medical_package.MedicalRecord
import com.htrackk.myapplication.data.remote.model.medical_package.SurgicalHistory
import com.htrackk.myapplication.ui.AppViewModelProvider

@Composable
fun MedicalRecordScreen(
    user: User,
    viewModel: MedicalRecordViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {

    viewModel.getMedicalRecordList(userId = user.id)

    val medicalRecordList by viewModel.medicalRecord.collectAsState()
    val medicalRecordUiState by viewModel.medicalRecordUiState.collectAsState()


    Surface(
        modifier = Modifier
            .fillMaxHeight()
    ) {
        when (medicalRecordUiState) {
            MedicalRecordUiState.SUCCESS ->
            {
                LazyRow() {
                    items(items = medicalRecordList) { medicalRecord ->
                        MedicalRecordFile(medicalRecord = medicalRecord)
                    }
                }
            }
            MedicalRecordUiState.LOADING ->
            {
                Text(text = "Loading")
            }
            MedicalRecordUiState.ERROR ->
            {
                Text(text = "Error")
            }
        }
    }

}


@Composable
fun MedicalRecordFile(
    medicalRecord: MedicalRecord,
    modifier: Modifier = Modifier
) {

    Card (
        modifier = modifier
            .padding(16.dp)
            .background(Color.White)
            .border(width = 2.dp, color = Color.Black, shape = RoundedCornerShape(8.dp))
            .fillMaxHeight(),
        colors = CardColors(Color.White, Color.Black, Color.White,  Color.White)
    ){
        Column(
            modifier = Modifier
                .padding(32.dp)
        ) {
            TextField(
                value = medicalRecord.condition,
                onValueChange = {},
                enabled = false,
                readOnly = true,
                label = {
                    Text(
                        text = "Condition:",
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        fontSize = 16.sp
                    )
                },
                colors = com.htrackk.myapplication.data.local.TextFieldColors.customTextFieldColors,
                singleLine = false

            )
            TextField(
                value = medicalRecord.present.toString(),
                onValueChange = {},
                enabled = false,
                readOnly = true,
                label = {
                    Text(
                        text = "Present:",
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        fontSize = 16.sp
                    )
                },
                colors = com.htrackk.myapplication.data.local.TextFieldColors.customTextFieldColors,
                singleLine = false
            )
            TextField(
                value = medicalRecord.recordId.toString(),
                onValueChange = {},
                enabled = false,
                readOnly = true,
                label = {
                    Text(
                        text = "Record id:",
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        fontSize = 16.sp
                    )
                },
                colors = com.htrackk.myapplication.data.local.TextFieldColors.customTextFieldColors
            )
            //Spacer(modifier = Modifier.height(12.dp))
            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = "Surgical History: ",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
                LazyColumn() {
                    items(items = medicalRecord.surgicalHistoryList) { surgicalHistory ->

                        Text(
                            text = surgicalHistory.surgery,
                            fontSize = 16.sp,
                            textDecoration = TextDecoration.Underline,
                            modifier = Modifier
                                .padding(8.dp)
                        )

                    }


                }
            }
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewMedicalFile() {
    MedicalRecordFile(
        medicalRecord = MedicalRecord(
            "Heart disease",
            true, 1255,
            listOf<SurgicalHistory>(
                SurgicalHistory(123, 421, "surgery on open heart"),
                SurgicalHistory(123, 421, "surgery on open heart"),
                SurgicalHistory(123, 421, "surgery on open heart"),
                SurgicalHistory(123, 421, "surgery on open heart"),
                SurgicalHistory(123, 421, "surgery on open heart"),
                SurgicalHistory(123, 421, "surgery on open heart"),
                SurgicalHistory(123, 421, "surgery on open heart"),
                SurgicalHistory(123, 421, "surgery on open heart"),
                SurgicalHistory(123, 421, "surgery on open heart"),
                SurgicalHistory(123, 421, "surgery on open heart"),
                SurgicalHistory(123, 421, "surgery on open heart"),
                SurgicalHistory(123, 421, "surgery on open heart"),
                SurgicalHistory(123, 421, "surgery on open heart"),
                SurgicalHistory(123, 421, "surgery on open heart"),
                SurgicalHistory(123, 421, "surgery on open heart"),
                SurgicalHistory(123, 421, "surgery on open heart"),
                SurgicalHistory(123, 421, "surgery on open heart"),
                SurgicalHistory(123, 421, "surgery on open heart"),
                SurgicalHistory(123, 421, "surgery on open heart"),
                SurgicalHistory(123, 421, "surgery on open heart"),
                SurgicalHistory(123, 421, "surgery on open heart"),

                )
        )
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewMedicalRecordScreen() {
    MedicalRecordScreen(
        User(0, "", "", "", "", "", "", "", "", "")
    )
}