package com.devabhi.appwithapi.android

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    // Define color scheme for dark and light themes
    val colors = if (darkTheme) {
        darkColorScheme(
            primary = Color(0xFFBB86FC), // Light purple for primary color
            secondary = Color(0xFF03DAC5), // Cyan for secondary color
            tertiary = Color(0xFF3700B3), // Darker purple for tertiary color
            background = Color(0xFF121212), // Dark background
            surface = Color(0xFF1E1E1E), // Dark surface
            onPrimary = Color.White, // White text on primary color
            onSecondary = Color.Black, // Black text on secondary color
            onBackground = Color.White, // White text on background
            onSurface = Color.White // White text on surface
        )
    } else {
        lightColorScheme(
            primary = Color(0xFF6200EE), // Purple for primary color
            secondary = Color(0xFF03DAC5), // Cyan for secondary color
            tertiary = Color(0xFF3700B3), // Dark purple for tertiary color
            background = Color(0xFFF5F5F5), // Light background
            surface = Color(0xFFFFFFFF), // White surface
            onPrimary = Color.White, // White text on primary color
            onSecondary = Color.Black, // Black text on secondary color
            onBackground = Color.Black, // Black text on background
            onSurface = Color.Black // Black text on surface
        )
    }

    // Define typography for the app
    val typography = Typography(
        bodyMedium = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        ),
        titleLarge = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
        ),
        headlineMedium = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp
        )
    )

    // Define shapes for UI elements
    val shapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(8.dp), // Increased for a more modern look
        large = RoundedCornerShape(12.dp) // Large corners for larger components
    )

    // Apply the theme
    MaterialTheme(
        colorScheme = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}
