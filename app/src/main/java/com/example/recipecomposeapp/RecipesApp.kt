package com.example.recipecomposeapp

import android.content.Intent
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.recipecomposeapp.ui.navigation.BottomNavigation
import com.example.recipecomposeapp.ui.navigation.AppNavHost
import com.example.recipecomposeapp.ui.navigation.Destination
import com.example.recipecomposeapp.ui.theme.RecipeComposeAppTheme

@Composable
fun RecipesApp(deepLinkIntent: Intent?) {

    val navHost = rememberNavController()

    RecipeComposeAppTheme {
        Scaffold(
            containerColor = MaterialTheme.colorScheme.background,
            bottomBar = {
                BottomNavigation(
                    {
                        navHost.navigate(Destination.Categories.route)
                    }, {
                        navHost.navigate(Destination.Favorites.route)
                    },
                    modifier = Modifier.navigationBarsPadding()
                )
            }
        ) { innerPadding ->
            AppNavHost(navHost, innerPadding,
                deepLinkIntent)
        }
    }
}

@Composable
@Preview
fun RecipesAppPreview() {
    RecipesApp(null)
}