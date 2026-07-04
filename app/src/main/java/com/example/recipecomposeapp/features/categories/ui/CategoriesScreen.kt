package com.example.recipecomposeapp.features.categories.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.recipecomposeapp.core.ui.ScreenHeader
import com.example.recipecomposeapp.core.ui.theme.Dimensions
import com.example.recipecomposeapp.core.utils.ASSETS_URI_PREFIX
import com.example.recipecomposeapp.data.repository.RecipesRepository
import com.example.recipecomposeapp.features.categories.presentation.CategoriesViewModel
import com.example.recipecomposeapp.features.categories.presentation.model.CategoryUiModel
import com.example.recipecomposeapp.features.categories.presentation.model.toUiModel

const val CATEGORIES_IMAGE_URL = ASSETS_URI_PREFIX + "bcg_categories.png"

@Composable
fun CategoriesScreen(innerPadding: PaddingValues, onCategoryClick: (Int, String, String) -> Unit) {

    val categoryViewModel: CategoriesViewModel = viewModel()
    val categoriesState by categoryViewModel.categoriesUiState.collectAsState()

    Column(modifier = Modifier.padding(innerPadding)) {
        ScreenHeader("КАТЕГОРИИ", CATEGORIES_IMAGE_URL)

        if (categoriesState.isLoading) {
            CircularProgressIndicator()
        } else if (categoriesState.error != null) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("Ошибка при загрузке категорий: ${categoriesState.error}")
                Spacer(Modifier.padding(vertical = 15.dp))

                Button(
                    onClick = { categoryViewModel.loadCategories() },
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text("Повторить")
                }
            }
        } else {
            Column {
                CategoryCards(categoriesState.categories, onCategoryClick)
            }
        }
    }
}

@Composable
fun CategoryCards(categories: List<CategoryUiModel>, onCategoryClick: (Int, String, String) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .padding(horizontal = Dimensions.paddingLarge)
            .padding(top = Dimensions.paddingLarge, bottom = Dimensions.paddingSmall),
        horizontalArrangement = Arrangement.spacedBy(Dimensions.paddingLarge),
        verticalArrangement = Arrangement.spacedBy(Dimensions.paddingLarge),
        content = {
            items(categories) { category ->
                CategoryItem(
                    category, onCategoryClick
                )
            }
        }
    )
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun CategoriesScreenPreview() {
    CategoriesScreen(PaddingValues(Dimensions.paddingLarge), {} as (Int, String, String) -> Unit)
}