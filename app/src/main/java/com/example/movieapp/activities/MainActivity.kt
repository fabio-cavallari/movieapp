package com.example.movieapp.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.R
import com.example.movieapp.navigation.MovieAppNavHost
import com.example.movieapp.navigation.homeScreenRoute
import com.example.movieapp.ui.theme.MovieAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            App(navController = navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(navController: NavHostController) {
    MovieAppTheme {
        Surface {
            Scaffold(
                topBar = {
                    val backStackEntry by navController.currentBackStackEntryAsState()
                    val canNavigateBack = backStackEntry?.destination?.route != homeScreenRoute
                    CenterAlignedTopAppBar(
                        title = {
                            Row(
                                Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center,
                            ) {
                                Text(
                                    text = stringResource(id = R.string.app_name),
                                )
                            }
                        },
                        navigationIcon = {
                            if (canNavigateBack) {
                                IconButton(onClick = { navController.popBackStack() }) {
                                    Icon(
                                        imageVector = Icons.Default.ArrowBack,
                                        contentDescription = "goBack"
                                    )
                                }
                            }
                        }
                    )
                }
            ) { paddingValues ->
                Box(
                    Modifier.padding(paddingValues)
                ) {
                    MovieAppNavHost(navController = navController)
                }
            }
        }
    }
}

//@Preview(showSystemUi = true)
//@Composable
//private fun AppPreview() {
//    App {
//        val homeScreenUiState =
//            HomeScreenUiState(movieList = movieListSample, uiState = UiState.SUCCESS)
//        HomeScreen(homeScreenUiState)
//    }
//}