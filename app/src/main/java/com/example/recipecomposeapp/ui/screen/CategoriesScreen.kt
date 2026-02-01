package com.example.recipecomposeapp.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipecomposeapp.ui.theme.RecipesAppTypography

@Composable
fun CategoriesScreen(innerPadding: PaddingValues) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(innerPadding),
        contentAlignment = Alignment.Center
    ) {
        Text("Категории", style = RecipesAppTypography.displayLarge)
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun CategoriesScreenPreview() {
    CategoriesScreen(PaddingValues(16.dp))
}