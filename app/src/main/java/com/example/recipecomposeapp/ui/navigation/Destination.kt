package com.example.recipecomposeapp.ui.navigation

sealed class Destination(val route: String) {

    data object Categories : Destination("categories")
    data object Favorites : Destination("favorites")

    data object Recipe : Destination("recipe/{recipeId}") {
        fun createRoute(recipeId: Int) = "recipe/$recipeId"
    }

    data object Recipes : Destination("recipes/{categoryId}") {
        fun createRoute(categoryId: Int) = "recipes/$categoryId"
    }
}