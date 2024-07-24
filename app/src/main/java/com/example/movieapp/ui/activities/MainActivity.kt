package com.example.movieapp.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.movieapp.model.movieListSample
import com.example.movieapp.ui.screens.HomeScreen
import com.example.movieapp.ui.states.HomeScreenUiState
import com.example.movieapp.ui.theme.MovieAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App {
                HomeScreen(uiState = HomeScreenUiState(movieListSample))
            }
        }
    }
}

@Composable
fun App(content: @Composable () -> Unit = {}) {
    MovieAppTheme {
        Surface {
            Scaffold { paddingValues ->
                Box(
                    Modifier.padding(paddingValues)
                ) {
                    content()
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun AppPreview() {
    App {
        HomeScreen(uiState = HomeScreenUiState(movieListSample))
    }
}