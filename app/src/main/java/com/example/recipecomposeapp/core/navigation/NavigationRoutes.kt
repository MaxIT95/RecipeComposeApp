package com.example.recipecomposeapp.core.navigation

const val DEEP_LINK_SCHEME = "recipeapp"
const val DEEP_LINK_BASE_URL = "https://recipes.androidsprint.ru"

fun createRecipeDeepLink(recipeId: Int): String {
    return "$DEEP_LINK_BASE_URL/recipe/$recipeId"
}