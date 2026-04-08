package com.example.recipecomposeapp.ui.navigation

import android.content.Intent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.recipecomposeapp.data.repository.getRecipeById
import com.example.recipecomposeapp.ui.categories.screen.CategoriesScreen
import com.example.recipecomposeapp.ui.details.RecipeDetailsScreen
import com.example.recipecomposeapp.ui.favorites.screen.FavoritesScreen
import com.example.recipecomposeapp.ui.recipes.model.RecipeUiModel
import com.example.recipecomposeapp.ui.recipes.screen.RecipesScreen
import com.example.recipecomposeapp.utils.FavoriteDataStoreManager
import com.example.recipecomposeapp.utils.KEY_RECIPE_OBJECT
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun AppNavHost(
    navHostController: NavHostController,
    paddingValues: PaddingValues,
    deepLinkIntent: Intent?
) {
    val context = LocalContext.current
    val favoriteDataStoreManager = remember {
        FavoriteDataStoreManager(context)
    }

    LaunchedEffect(deepLinkIntent) {
        deepLinkIntent?.data?.let { uri ->
            val recipeId: Int? = when (uri.scheme) {
                DEEP_LINK_SCHEME ->
                    // recipeapp://recipe/123 → host="recipe", pathSegments=[123]
                    if (uri.host == "recipe") uri.pathSegments[0].toIntOrNull() else null

                "https", "http" ->
                    // https://.../recipe/123 → pathSegments=[recipe, 123]
                    if (uri.pathSegments[0] == "recipe") uri.pathSegments[1].toIntOrNull() else null

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
            arguments = listOf(navArgument("categoryId") { type = NavType.IntType })
        ) { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getInt("categoryId") ?: 0
            RecipesScreen(categoryId = categoryId, onRecipeClick = { id, recipe ->
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
        ) { backStackEntry ->
            val recipe =
                navHostController.previousBackStackEntry?.savedStateHandle?.get<RecipeUiModel>(
                    KEY_RECIPE_OBJECT
                )

            val coroutineScope = rememberCoroutineScope()

            if (recipe != null) {

                val isFavorite by favoriteDataStoreManager
                    .isFavorite(recipe.id)
                    .collectAsState(initial = false)

                RecipeDetailsScreen(
                    recipe,
                    paddingValues,
                    isFavorite = isFavorite,
                    onFavoriteToggle = {
                        onToggleFavorites(
                            it, recipe.id,
                            favoriteDataStoreManager,
                            coroutineScope
                        )
                    })
            } else {
                val recipeId = backStackEntry.arguments?.getInt("recipeId") ?: 0
                val recipe = getRecipeById(recipeId) // ищем в stub-данных по ID

                recipe?.let { it ->

                    val isFavorite by favoriteDataStoreManager
                        .isFavorite(recipe.id)
                        .collectAsState(initial = false)

                    RecipeDetailsScreen(
                        it,
                        paddingValues,
                        isFavorite = isFavorite,
                        onFavoriteToggle = {
                            onToggleFavorites(
                                it,
                                recipe.id,
                                favoriteDataStoreManager,
                                coroutineScope
                            )
                        }
                    )
                }
            }
        }

        composable(
            route = Destination.Categories.route,
        ) {
            CategoriesScreen(paddingValues) { category ->
                navHostController.navigate("recipes/${category.id}")
            }
        }

        composable(route = Destination.Favorites.route) {
            FavoritesScreen(paddingValues)
        }
    }
}

private fun onToggleFavorites(
    isFavorite: Boolean,
    recipeId: Int,
    favoriteDataStoreManager: FavoriteDataStoreManager,
    coroutineScope: CoroutineScope
) {
    coroutineScope.launch {
        if (isFavorite) {
            favoriteDataStoreManager.removeFromFavorites(recipeId)
        } else {
            favoriteDataStoreManager.addFavorite(recipeId)
        }
    }
}