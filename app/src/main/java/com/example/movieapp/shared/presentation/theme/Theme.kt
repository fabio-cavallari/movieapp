package com.example.movieapp.shared.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = primaryDark,
    primaryContainer = primaryContainerLight,
    secondary = secondaryDark,
    tertiary = tertiaryDark,
    tertiaryContainer = tertiaryContainerDark,
    background = backgroundDark,
    surface = surfaceDark,
    onPrimary = onPrimaryDark,
    onSecondary = onSecondaryDark,
    onTertiary = onTertiaryDark,
    onTertiaryContainer = onTertiaryContainerDark,
    onBackground = onBackgroundDark,
    onSurface = onSurfaceDark,
    inverseOnSurface = inverseOnSurfaceDark
)

private val LightColorScheme = lightColorScheme(
    primary = primaryLight,
    primaryContainer = primaryContainerLight,
    secondary = secondaryLight,
    tertiary = tertiaryLight,
    tertiaryContainer = tertiaryContainerLight,
    background = backgroundLight,
    surface = surfaceLight,
    onPrimary = onPrimaryLight,
    onSecondary = onSecondaryLight,
    onTertiary = onTertiaryLight,
    onTertiaryContainer = onTertiaryContainerLight,
    onBackground = onBackgroundLight,
    onSurface = onSurfaceLight,
    inverseOnSurface = inverseOnSurfaceLight
)

@Composable
fun MovieAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}