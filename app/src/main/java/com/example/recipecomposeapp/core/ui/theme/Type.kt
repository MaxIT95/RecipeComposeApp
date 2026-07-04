package com.example.recipecomposeapp.core.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)

val RecipesAppTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = montserratAlternatesFontFamily,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 24.sp,
        fontSize = 20.sp),
    titleMedium = TextStyle(
        fontFamily = montserratAlternatesFontFamily,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 19.sp,
        fontSize = 16.sp),
    bodyMedium = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.Medium,
        lineHeight = 16.sp,
        fontSize = 14.sp),
    bodySmall = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.Medium,
        lineHeight = 16.sp,
        fontSize = 12.sp),
    labelLarge = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.Medium,
        lineHeight = 19.sp,
        fontSize = 16.sp)
)

@Preview(showBackground = true)
@Composable
fun TypographyPreview() {
    RecipeComposeAppTheme {
        Column(modifier = Modifier.padding(Dimensions.paddingLarge),
            verticalArrangement = Arrangement.spacedBy(Dimensions.paddingMedium)) {
            Text("displayLarge - Заголовки экранов", style = RecipesAppTypography.displayLarge)
            Text("titleMedium - Карточки", style = RecipesAppTypography.titleMedium)
            Text("bodyMedium - Основной текст", style = RecipesAppTypography.bodyMedium)
            Text("bodySmall - Мелкий текст", style = RecipesAppTypography.bodySmall)
            Text("labelLarge - Кнопки", style = RecipesAppTypography.labelLarge)
        }
    }
}