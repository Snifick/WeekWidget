package com.example.letstry.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = clrBot,
    primaryVariant = Purple700,
    secondary = Teal200,
    background = clrBot,
    surface = clrBot,
    secondaryVariant = clrBot,
    onPrimary = Color.Black,
    onSurface = Color.Black,
    onBackground = Color.Black,
    onSecondary = Color.Black

    
)

private val LightColorPalette = lightColors(
    primary = clrBot,
    primaryVariant = clrBot,
    secondary = clrBot,
    background = clrBot,
    surface = clrBot,
    secondaryVariant = clrBot,
    onPrimary = Color.Black,
    onSurface = Color.Black,
    onBackground = Color.Black,
    onSecondary = Color.Black






)

@Composable
fun LetsTryTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}