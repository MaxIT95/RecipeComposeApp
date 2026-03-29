package com.example.recipecomposeapp.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.recipecomposeapp.ui.categories.screen.CategoriesScreen
import com.example.recipecomposeapp.ui.details.RecipeDetailsScreen
import com.example.recipecomposeapp.ui.favorites.screen.FavoritesScreen
import com.example.recipecomposeapp.ui.recipes.model.RecipeUiModel
import com.example.recipecomposeapp.ui.recipes.screen.RecipesScreen
import com.example.recipecomposeapp.utils.KEY_RECIPE_OBJECT

@Composable
fun AppNavHost(
    navHostController: NavHostController,
    paddingValues: PaddingValues
) {
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
        ) { _ ->
            val recipe = navHostController.previousBackStackEntry?.savedStateHandle?.get<RecipeUiModel>(KEY_RECIPE_OBJECT)

            if (recipe != null) {
                RecipeDetailsScreen(recipe, paddingValues)
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