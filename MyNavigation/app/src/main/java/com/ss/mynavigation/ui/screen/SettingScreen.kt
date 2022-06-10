package com.ss.mynavigation.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun SettingScreen() {
    Box(modifier = Modifier
        .background(Color.Yellow)
        .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Setting screen",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Color.DarkGray
        )
    }
}

@Preview
@Composable
fun PreviewSettingScreen() {
    SettingScreen()
}