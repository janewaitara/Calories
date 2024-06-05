package com.mumbicodes.ui.presentation.theme

import android.app.Activity
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme =
    lightColorScheme(
        background = GhostWhite,
        primary = Orange,
        onPrimary = OrangeLighter,
        primaryContainer = OrangeLightest,
        secondary = Blue,
        onSecondary = BlueLighter,
        secondaryContainer = BlueLightest,
        error = Red,
        onError = RedLighter,
        errorContainer = RedLightest,
        surface = Color.White
    )

// Use with MaterialTheme.ColorScheme.onWarning
val ColorScheme.inputFieldBackground: Color
    get() = AntiflashWhite

val ColorScheme.strongText: Color
    get() = RichBlack

val ColorScheme.normalText: Color
    get() = Onyx

@Composable
fun CaloriesTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = LightColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = true
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}