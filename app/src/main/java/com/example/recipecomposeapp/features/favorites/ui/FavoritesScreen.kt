package com.example.recipecomposeapp.features.favorites.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipecomposeapp.core.ui.ScreenHeader
import com.example.recipecomposeapp.core.ui.theme.Dimensions
import com.example.recipecomposeapp.core.ui.theme.RecipesAppTypography
import com.example.recipecomposeapp.core.utils.ASSETS_URI_PREFIX
import com.example.recipecomposeapp.core.utils.FavoriteDataStoreManager
import com.example.recipecomposeapp.data.repository.RecipesRepository
import com.example.recipecomposeapp.features.recipes.presentation.model.RecipeUiModel
import com.example.recipecomposeapp.features.recipes.ui.ReceiptItem
import kotlinx.coroutines.flow.map

const val FAVORITES_IMAGE_URL = ASSETS_URI_PREFIX + "bcg_favorites.png"

@Composable
fun FavoritesScreen(
    innerPadding: PaddingValues, repository: RecipesRepository,
    favoriteDataStoreManager: FavoriteDataStoreManager,
    onRecipeClick: (Int, RecipeUiModel) -> Unit
) {
    Column(modifier = Modifier.padding(innerPadding)) {
        ScreenHeader("ИЗБРАННОЕ", FAVORITES_IMAGE_URL)
        Spacer(Modifier.padding(vertical = 10.dp))

        val recipes by remember(favoriteDataStoreManager, repository) {
            favoriteDataStoreManager.getFavoriteIdsFlow().map { ids ->
                ids.mapNotNull { id -> repository.getRecipeById(id.toIntOrNull()) }
            }
        }
            .collectAsState(initial = emptyList())

        if (recipes.isEmpty()) {
            Text(
                text = "Список избранных рецептов пуст",
                color = MaterialTheme.colorScheme.primary,
                style = RecipesAppTypography.displayLarge,
                textAlign = TextAlign.Center
            )
        } else {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = Dimensions.paddingLarge)
            ) {
                items(
                    items = recipes,
                    key = { it.id }) {
                    ReceiptItem(
                        it,
                        onRecipeClick
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun FavoritesScreenPreview() {
    FavoritesScreen(
        PaddingValues(Dimensions.paddingLarge),
        RecipesRepository(),
        FavoriteDataStoreManager(LocalContext.current), { _, _ -> }
    )
}