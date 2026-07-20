package com.example.recipecomposeapp.core.navigation

import android.content.Intent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.recipecomposeapp.core.utils.KEY_RECIPE_OBJECT
import com.example.recipecomposeapp.features.categories.ui.CategoriesScreen
import com.example.recipecomposeapp.features.details.ui.RecipeDetailsScreen
import com.example.recipecomposeapp.features.favorites.ui.FavoritesScreen
import com.example.recipecomposeapp.features.recipes.ui.RecipesScreen
import kotlinx.coroutines.delay
import java.net.URLEncoder

@Composable
fun AppNavHost(
    navHostController: NavHostController,
    paddingValues: PaddingValues,
    deepLinkIntent: Intent?
) {
    LaunchedEffect(deepLinkIntent) {
        deepLinkIntent?.data?.let { uri ->
            val recipeId: Int? = when (uri.scheme) {
                DEEP_LINK_SCHEME ->
                    if (uri.host == "recipe" && uri.pathSegments.isNotEmpty())
                        uri.pathSegments[0].toIntOrNull() else null

                "https", "http" ->
                    if (uri.pathSegments.size >= 2 && uri.pathSegments[0] == "recipe")
                        uri.pathSegments[1].toIntOrNull() else null

                else -> null
            }

            if (recipeId != null) {
                delay(100) // даем время на инициализацию графа
                navHostController.navigate(Destination.Recipe.createRoute(recipeId))
            }
        }
    }

    NavHost(
        navController = navHostController,
        startDestination = Destination.Categories.route
    ) {
        composable(
            route = Destination.Recipes.route,
            arguments = listOf(
                navArgument("categoryId") { type = NavType.IntType },
                navArgument("categoryTitle") { type = NavType.StringType },
                navArgument("categoryImageUrl") { type = NavType.StringType },

                )
        ) {
            RecipesScreen(onRecipeClick = { id, recipe ->
                navHostController.currentBackStackEntry?.savedStateHandle?.set(
                    KEY_RECIPE_OBJECT,
                    recipe
                )
                navHostController.navigate(Destination.Recipe.createRoute(id))
            }, innerPadding = paddingValues)
        }

        composable(
            route = Destination.Recipe.route,
            arguments = listOf(navArgument("recipeId") { type = NavType.IntType })
        ) {
            RecipeDetailsScreen(
                paddingValues)
        }

        composable(
            route = Destination.Categories.route,
        ) {
            CategoriesScreen(paddingValues) { id, categoryTitle, categoryImageUrl ->

                val encodedTitle = URLEncoder.encode(categoryTitle, "UTF-8")
                val encodedImageUrl = URLEncoder.encode(categoryImageUrl, "UTF-8")
                navHostController.navigate("recipes/$id/$encodedTitle/$encodedImageUrl")
            }
        }

        composable(route = Destination.Favorites.route) {
            FavoritesScreen(
                paddingValues,
                onRecipeClick = { id, recipe ->
                    navHostController.currentBackStackEntry?.savedStateHandle?.set(
                        KEY_RECIPE_OBJECT,
                        recipe
                    )
                    navHostController.navigate(Destination.Recipe.createRoute(id))
                })
        }
    }
}