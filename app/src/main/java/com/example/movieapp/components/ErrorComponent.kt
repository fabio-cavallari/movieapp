package com.example.movieapp.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp.intents.HomeIntent

@Composable
fun ErrorComponent(errorMessage: String, buttonText: String, onButtonClick: () -> Unit = {}) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(48.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(
            text = errorMessage,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onButtonClick
        ) {
            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = "refresh"
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = buttonText)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ErrorComponentPreview() {
    ErrorComponent(errorMessage = "error message", buttonText = "try againd")
}