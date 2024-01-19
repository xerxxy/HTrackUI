package com.track.myapplication.ui.screens.biometrics

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.track.myapplication.R
import com.track.myapplication.data.remote.model.User


@Composable
fun ChartScreen(
    user: User
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
        ) {

            GridBox(
                title = listOf<String>("Heart rate", "Steps", "Sleep", "Oxygen"),
                secondTitle = listOf<String>("96 BPM", "8,421 Steps", "7.3 Hours", "98% Sat."),
                icon = listOf(
                    R.drawable.heart_rate,
                    R.drawable.steps_icon,
                    R.drawable.sleep_icon,
                    R.drawable.oxygen_icon
                ),
                colors = listOf(
                    listOf(Color.Green.toArgb(), Color.Blue.toArgb()),
                    listOf(Color.Green.toArgb(), Color.Blue.toArgb()),
                    listOf(Color.Green.toArgb(), Color.Blue.toArgb()),
                    listOf(Color.Green.toArgb(), Color.Blue.toArgb())
                ),
                onClickHeartRate = { /* TODO  */ },
                onClickSteps = { /* TODO  */ },
                onClickSleep = { /* TODO  */ },
                onClickOxygen = { /* TODO  */ })
        }

        Image(
            painter = painterResource(id = R.drawable.hear_rate_dummy_picture),
            contentDescription = "Dummy graph",
            modifier = Modifier.padding(top = 256.dp)
        )
    }

}

@Composable
fun ChartInfoBox(
    title: String,
    secondTitle: String,
    icon: Int,
    color: List<Int>,
    onClickBox: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .height(132.dp)
            .width(204.dp)
            .padding(16.dp)
            .drawBehind {
                val brush = Brush.linearGradient(
                    listOf(
                        Color(color[0]),
                        Color(color[1])
                    )
                )
                drawRoundRect(
                    brush,
                    cornerRadius = CornerRadius(10.dp.toPx())
                )
            }
            .clickable { onClickBox() }

    ) {
        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(
                    start = 12.dp,
                    top = 8.dp
                )
        ) {
            Icon(
                painterResource(id = icon),
                contentDescription = "Heart Icon",
                tint = Color.White,
                modifier = Modifier
                    .size(24.dp)

            )
            Text(
                text = title,
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraLight,
                color = Color.White,
                modifier = Modifier
                    .padding(
                        start = 4.dp,
                        bottom = 4.dp
                    )
            )
        }

        Text(
            text = secondTitle,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    bottom = 12.dp
                )
        )
    }
}

@Composable
fun GridBox(
    title: List<String>,
    secondTitle: List<String>,
    icon: List<Int>,
    colors: List<List<Int>>,
    onClickHeartRate: () -> Unit,
    onClickSteps: () -> Unit,
    onClickSleep: () -> Unit,
    onClickOxygen: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = Modifier
    ) {
        Row(
            modifier = modifier
        ) {
            ChartInfoBox(
                title = title[0],
                secondTitle = secondTitle[0],
                icon = icon[0],
                color = colors[0],
                onClickBox = onClickHeartRate,
                modifier = Modifier
                    .weight(1f)
            )

            ChartInfoBox(
                title = title[1],
                secondTitle = secondTitle[1],
                icon = icon[1],
                color = colors[1],
                onClickBox = onClickSteps,
                modifier = Modifier
                    .weight(1f)
            )
        }

        Row(
            modifier = modifier
        ) {
            ChartInfoBox(
                title = title[2],
                secondTitle = secondTitle[2],
                icon = icon[2],
                color = colors[2],
                onClickBox = onClickSleep,
                modifier = Modifier
                    .weight(1f)
            )

            ChartInfoBox(
                title = title[3],
                secondTitle = secondTitle[3],
                icon = icon[3],
                color = colors[3],
                onClickBox = onClickOxygen,
                modifier = Modifier
                    .weight(1f)
            )
        }
    }

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun previewChartScreen() {
    ChartScreen(user = User(424, "", "", "", "", "", "", "", "", ""))
}


