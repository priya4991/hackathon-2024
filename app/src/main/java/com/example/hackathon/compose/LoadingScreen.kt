package com.example.hackathon.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LoadingScreen() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize().
        background(Color.Transparent)
    ) {
        CircularProgressIndicator(
            color = Color.LightGray,
            modifier = Modifier.size(70.dp),
            strokeWidth = 15.dp,
            trackColor = Color.White,
            strokeCap = StrokeCap.Round
        )
    }
}

@Preview
@Composable
fun LoadingScreenView() {
    LoadingScreen()
}