package com.example.recipecomposeapp.features.recipes.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.recipecomposeapp.core.ui.ScreenHeader
import com.example.recipecomposeapp.core.ui.theme.Dimensions
import com.example.recipecomposeapp.features.recipes.presentation.RecipesViewModel

@Composable
fun RecipesScreen(
    onRecipeClick: (Int) -> Unit,
    innerPadding: PaddingValues,
) {

    val recipesViewModel: RecipesViewModel = viewModel()
    val recipesState by recipesViewModel.recipesUiState.collectAsState()

    Column(
        modifier = Modifier.padding(paddingValues = innerPadding)
    ) {
        ScreenHeader(
            recipesState.categoryTitle!!.uppercase(), recipesState.categoryImageUrl!!
        )

        if (recipesState.isLoading) {
            CircularProgressIndicator()
        } else if (recipesState.error != null) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("Ошибка при загрузке категорий: ${recipesState.error}")
                Spacer(Modifier.padding(vertical = 15.dp))

                Button(
                    onClick = { recipesViewModel.loadRecipes() },
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text("Повторить")
                }
            }
        } else if (recipesState.recipes.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("Рецепты в категории ${recipesState.categoryTitle} отсутствуют")
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(
                        horizontal = Dimensions.paddingLarge,
                        vertical = Dimensions.paddingMedium
                    )
            ) {
                items(
                    items = recipesState.recipes,
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
fun RecipesScreenPreview() {
    RecipesScreen(
        { _ -> }, PaddingValues(0.dp)
    )
}