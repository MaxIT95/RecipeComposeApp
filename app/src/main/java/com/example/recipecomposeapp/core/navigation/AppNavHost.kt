package com.example.recipecomposeapp.core.navigation

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.recipecomposeapp.core.utils.FavoriteDataStoreManager
import com.example.recipecomposeapp.core.utils.KEY_RECIPE_OBJECT
import com.example.recipecomposeapp.data.repository.RecipesRepository
import com.example.recipecomposeapp.features.categories.ui.CategoriesScreen
import com.example.recipecomposeapp.features.details.ui.RecipeDetailsScreen
import com.example.recipecomposeapp.features.favorites.ui.FavoritesScreen
import com.example.recipecomposeapp.features.recipes.presentation.model.RecipeUiModel
import com.example.recipecomposeapp.features.recipes.ui.RecipesScreen
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
    val recipesRepository = remember {
        RecipesRepository()
    }

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
            arguments = listOf(navArgument("categoryId") { type = NavType.IntType })
        ) { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getInt("categoryId") ?: 0
            RecipesScreen(categoryId = categoryId, onRecipeClick = { id, recipe ->
                navHostController.currentBackStackEntry?.savedStateHandle?.set(
                    KEY_RECIPE_OBJECT,
                    recipe
                )
                navHostController.navigate(Destination.Recipe.createRoute(id))
            }, innerPadding = paddingValues, repository = recipesRepository)
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
                val recipe = recipesRepository.getRecipeById(recipeId) // ищем в stub-данных по ID

                recipe?.let { it ->

                    val isFavorite by favoriteDataStoreManager
                        .isFavorite(recipe.id)
                        .collectAsState(initial = false)
                    Log.i("+++", "iddd=${recipe.id} value $isFavorite")

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
            CategoriesScreen(paddingValues, { id, _, _ ->
                navHostController.navigate("recipes/$id")
            })
        }

        composable(route = Destination.Favorites.route) {
            FavoritesScreen(
                paddingValues,
                recipesRepository,
                favoriteDataStoreManager,
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