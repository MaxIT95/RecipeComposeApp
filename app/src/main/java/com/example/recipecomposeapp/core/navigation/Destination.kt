package com.example.recipecomposeapp.core.navigation

import java.net.URLEncoder

sealed class Destination(val route: String) {

    data object Categories : Destination("categories")
    data object Favorites : Destination("favorites")

    data object Recipe : Destination("recipe/{recipeId}") {
        fun createRoute(recipeId: Int) = "recipe/$recipeId"
    }

    data object Recipes : Destination("recipes/{categoryId}/{categoryTitle}/{categoryImageUrl}") {

        fun createRecipesRoute(categoryId: Int, title: String, imageUrl: String): String {
            return "recipes/$categoryId/${URLEncoder.encode(title, "UTF-8")}/${
                URLEncoder.encode(
                    imageUrl,
                    "UTF-8"
                )
            }"
        }
    }
}