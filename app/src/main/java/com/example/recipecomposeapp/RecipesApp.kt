package com.example.recipecomposeapp

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.recipecomposeapp.model.ScreenId
import com.example.recipecomposeapp.ui.categories.screen.CategoriesScreen
import com.example.recipecomposeapp.ui.navigation.BottomNavigation
import com.example.recipecomposeapp.ui.favorites.screen.FavoritesScreen
import com.example.recipecomposeapp.ui.recipes.RecipesScreen
import com.example.recipecomposeapp.ui.theme.BackgroundColor
import com.example.recipecomposeapp.ui.theme.RecipeComposeAppTheme

@Composable
fun RecipesApp() {

    var currentScreenState by remember { mutableStateOf(ScreenId.CATEGORIES) }

    RecipeComposeAppTheme {
        Scaffold(
            containerColor = MaterialTheme.colorScheme.background,
            bottomBar = {
                BottomNavigation(
                    {
                        currentScreenState = ScreenId.CATEGORIES
                    }, {
                        currentScreenState = ScreenId.FAVORITES
                    },
                    modifier = Modifier.navigationBarsPadding()
                )
            }
        ) { innerPadding ->
            when (currentScreenState) {
                ScreenId.CATEGORIES -> CategoriesScreen(innerPadding)
                ScreenId.FAVORITES -> FavoritesScreen(innerPadding)
                ScreenId.RECIPES -> RecipesScreen(innerPadding)
            }
        }
    }
}

@Composable
@Preview
fun RecipesAppPreview() {
    RecipesApp()
}