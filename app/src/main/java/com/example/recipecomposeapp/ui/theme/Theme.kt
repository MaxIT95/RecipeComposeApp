package com.example.recipecomposeapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = mainTitleColorDark,
    secondary = PurpleGrey80,
    tertiary = Pink80,
    background = backgroundDark,
    surface = backgroundDark,
    onBackground = mainTitleColorDark,
)

private val LightColorScheme = lightColorScheme(
    primary = mainTitleColor,
    secondary = PurpleGrey40,
    tertiary = Pink40,
    background = background,
    surface = background,
    onBackground = mainTitleColor,
)

val ColorScheme.subtitle: Color
    @Composable
    get() = if (isSystemInDarkTheme()) subtitileGrayColor else subtitileGrayColorDark

@Composable
fun RecipeComposeAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = RecipesAppTypography,
        content = content
    )
}