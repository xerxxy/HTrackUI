package com.raul.myapplication.ui.screens.assesments

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raul.myapplication.data.remote.model.Assessment
import com.raul.myapplication.data.remote.model.User
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.raul.myapplication.ui.AppViewModelProvider


@Composable
fun AssessmentsScreen(
    user: User,
    viewModel: AssessmentsViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val isLoading by viewModel.isLoading.collectAsState()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isLoading)
    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = {
            viewModel.getAssessmentList(userId = user.id)
        }) {
    viewModel.getAssessmentList(userId = user.id)

    Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            when (viewModel.assessmentUiState) {
                is AssessmentUiState.Success -> {
                    LazyColumn {
                        items(
                            count = (viewModel.assessmentUiState as AssessmentUiState.Success).assessments.size,
                        ) { index ->
                            AssessmentItem(
                                assessment = (viewModel.assessmentUiState as AssessmentUiState.Success).assessments[index]
                            )

                        }
                    }

                }

                is AssessmentUiState.Error -> {
                    Text(text = "ERROR")
                }

                is AssessmentUiState.Loading -> {
                    Text(text = "LOADING")
                }
            }
        }
    }
}


@Composable
fun AssessmentItem(
    assessment: Assessment = Assessment(424, "fdsf")
) {
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
                text = assessment.assessmentId.toString(),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Duration = ${assessment.assessment}",
                fontSize = 16.sp
            )
        }
    }
}