package com.example.recipecomposeapp

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.recipecomposeapp.model.ScreenId
import com.example.recipecomposeapp.ui.navigation.BottomNavigation
import com.example.recipecomposeapp.ui.screen.CategoriesScreen
import com.example.recipecomposeapp.ui.screen.FavoritesScreen
import com.example.recipecomposeapp.ui.theme.RecipeComposeAppTheme

@Composable
fun RecipesApp() {

    var currentScreenState by remember { mutableStateOf(ScreenId.CATEGORIES) }

    RecipeComposeAppTheme {
        Scaffold(
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
            if (currentScreenState == ScreenId.CATEGORIES) {
                CategoriesScreen(innerPadding)
            } else {
                FavoritesScreen(innerPadding)
            }
        }
    }
}

@Composable
@Preview
fun RecipesAppPreview() {
    RecipesApp()
}