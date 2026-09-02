package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

enum class AppThemeMode {
    SYSTEM,
    LIGHT,
    DARK
}

private val DarkColorScheme = darkColorScheme(
    primary = NavyBluePrimaryDark,
    onPrimary = NavyBlueOnPrimaryDark,
    primaryContainer = NavyBluePrimaryContainerDark,
    onPrimaryContainer = NavyBlueOnPrimaryContainerDark,
    secondary = NavyBlueSecondaryDark,
    onSecondary = NavyBlueOnSecondaryDark,
    secondaryContainer = NavyBlueSecondaryContainerDark,
    onSecondaryContainer = NavyBlueOnSecondaryContainerDark,
    tertiary = NavyBlueTertiaryDark,
    onTertiary = NavyBlueOnTertiaryDark,
    tertiaryContainer = NavyBlueTertiaryContainerDark,
    onTertiaryContainer = NavyBlueOnTertiaryContainerDark,
    background = NavyBlueBackgroundDark,
    onBackground = NavyBlueOnBackgroundDark,
    surface = NavyBlueSurfaceDark,
    onSurface = NavyBlueOnSurfaceDark,
    surfaceVariant = NavyBlueSurfaceVariantDark,
    onSurfaceVariant = NavyBlueOnSurfaceVariantDark,
    outline = NavyBlueOutlineDark
)

private val LightColorScheme = lightColorScheme(
    primary = NavyBluePrimaryLight,
    onPrimary = NavyBlueOnPrimaryLight,
    primaryContainer = NavyBluePrimaryContainerLight,
    onPrimaryContainer = NavyBlueOnPrimaryContainerLight,
    secondary = NavyBlueSecondaryLight,
    onSecondary = NavyBlueOnSecondaryLight,
    secondaryContainer = NavyBlueSecondaryContainerLight,
    onSecondaryContainer = NavyBlueOnSecondaryContainerLight,
    tertiary = NavyBlueTertiaryLight,
    onTertiary = NavyBlueOnTertiaryLight,
    tertiaryContainer = NavyBlueTertiaryContainerLight,
    onTertiaryContainer = NavyBlueOnTertiaryContainerLight,
    background = NavyBlueBackgroundLight,
    onBackground = NavyBlueOnBackgroundLight,
    surface = NavyBlueSurfaceLight,
    onSurface = NavyBlueOnSurfaceLight,
    surfaceVariant = NavyBlueSurfaceVariantLight,
    onSurfaceVariant = NavyBlueOnSurfaceVariantLight,
    outline = NavyBlueOutlineLight
)

@Composable
fun HumanSystemTheme(
    themeMode: AppThemeMode = AppThemeMode.SYSTEM,
    content: @Composable () -> Unit
) {
    val isDark = when (themeMode) {
        AppThemeMode.SYSTEM -> isSystemInDarkTheme()
        AppThemeMode.LIGHT -> false
        AppThemeMode.DARK -> true
    }

    val colorScheme = if (isDark) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
