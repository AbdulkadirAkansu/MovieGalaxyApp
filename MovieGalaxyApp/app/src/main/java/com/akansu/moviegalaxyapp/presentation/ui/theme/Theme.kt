package com.akansu.moviegalaxyapp.presentation.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

val DarkColorScheme = darkColorScheme(
    primary = Color.Black,
    onPrimary = Color.White,
    primaryContainer = Color.DarkGray,
    onPrimaryContainer = Color.White,
    secondary = Color.DarkGray,
    onSecondary = Color.White,
    secondaryContainer = Color.LightGray,
    onSecondaryContainer = Color.Black,
    tertiary = Color.Gray,
    onTertiary = Color.Black,
    background = Color.Black,
    surface = Color.Black,
    onBackground = Color.White,
    onSurface = Color.White
)

val LightColorScheme = lightColorScheme(
    primary = Color.Black,
    onPrimary = Color.Black,
    primaryContainer = Color.LightGray,
    onPrimaryContainer = Color.Black,
    secondary = Color.Gray,
    onSecondary = Color.Black,
    secondaryContainer = Color.DarkGray,
    onSecondaryContainer = Color.White,
    tertiary = Color.LightGray,
    onTertiary = Color.Black,
    background = Color.White,
    surface = Color.White,
    onBackground = Color.Black,
    onSurface = Color.Black
)

@Composable
fun MovieGalaxyAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val view = LocalView.current

    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
