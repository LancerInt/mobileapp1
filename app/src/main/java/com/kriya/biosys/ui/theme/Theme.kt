package com.kriya.biosys.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = Color(0xFF006D3F),
    onPrimary = Color.White,
    background = Color(0xFFF5F7F4),
    surface = Color.White,
    secondary = Color(0xFF386A20),
    onBackground = Color(0xFF1B1B1B)
)

@Composable
fun KriyaTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColors,
        typography = Typography,
        content = content
    )
}
