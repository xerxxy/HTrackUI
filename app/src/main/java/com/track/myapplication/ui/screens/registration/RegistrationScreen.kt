package com.track.myapplication.ui.screens.registration

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.track.myapplication.ui.components.NormalTextComponent

@Composable
fun RegistrationScreen() {

    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        NormalTextComponent(text = "ceva")
    }
}

@Composable
fun TestFieldForInput(

) {

}

@Preview
@Composable
fun previewRegistrationScreen() {
    RegistrationScreen()
}